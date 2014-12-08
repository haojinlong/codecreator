/**
 * # CreateWizard.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.commons.http.DefaultResult;
import net.haojinlong.codecreator.dao.plugin.common.CreateFromDbParam;
import net.haojinlong.codecreator.dao.plugin.common.LocalConfig;
import net.haojinlong.codecreator.dao.plugin.common.SourceType;
import net.haojinlong.codecreator.http.HttpDaoByClientBuilder;
import net.haojinlong.codecreator.http.HttpEntityByClientBuilder;
import net.haojinlong.codecreator.http.HttpMapperXmlByClientBuilder;
import net.haojinlong.codecreator.utils.ConnectionUtils;
import net.haojinlong.codecreator.utils.SourceFileUtils;
import net.haojinlong.codecreator.utils.TableUtils;
import net.haojinlong.tools.json.JsonUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
public class CreateWizard extends Wizard {
	static Logger logger = LoggerFactory.getLogger(CreateWizard.class);

	// 自动生成的配置文件地址
	static String CONFIG_FILE_NAME = ".net.haojinlong.codecreator.dao";

	// 配置文件中localConfig的关键字
	static String KEY_LOCAL_CONFIG = "_LOCAL_CONFIG";

	private IJavaProject project;

	// 读取的配置文件内容
	private Properties properties;

	private LocalConfig localConfig;

	private SourceSelectPage sourceSelectPage;

	private TypeSelectPage typeSelectPage;

	private DbInfoLoadPage dbInfoLoadPage;

	private DestSelectPage destSelectPage;

	private ConfigConfirmPage configConfirmPage;

	/**
	 * @param projectPath
	 */
	public CreateWizard(IJavaProject project) {
		super();
		this.project = project;
		this.properties = readConfig();
		String localConfigJson = properties.getProperty(KEY_LOCAL_CONFIG);
		if (localConfigJson != null) {
			localConfig = JsonUtils
					.fromJson(localConfigJson, LocalConfig.class);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		sourceSelectPage = new SourceSelectPage(localConfig);
		addPage(sourceSelectPage);
		typeSelectPage = new TypeSelectPage();
		addPage(typeSelectPage);
		dbInfoLoadPage = new DbInfoLoadPage();
		addPage(dbInfoLoadPage);
		destSelectPage = new DestSelectPage(project);
		addPage(destSelectPage);
		configConfirmPage = new ConfigConfirmPage(localConfig);
		addPage(configConfirmPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.
	 * IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == sourceSelectPage
				&& sourceSelectPage.getSourceType() == SourceType.db) {
			return typeSelectPage;
		}
		if (page == sourceSelectPage
				&& sourceSelectPage.getSourceType() == SourceType.config) {
			return configConfirmPage;
		}
		if (page == typeSelectPage) {
			dbInfoLoadPage.setDbType(typeSelectPage.getDbType());
			return dbInfoLoadPage;
		}
		if (page == dbInfoLoadPage) {
			return destSelectPage;
		}
		if (page == destSelectPage) {
			return null;
		}
		return super.getNextPage(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		SourceType type = sourceSelectPage.getSourceType();

		// 初始化参数
		if (type == SourceType.db) {
			CreateFromDbParam dbParam = new CreateFromDbParam();
			dbParam.setJarDir(dbInfoLoadPage.getJarDir());
			dbParam.setDriverName(dbInfoLoadPage.getDriverName());
			dbParam.setUrl(dbInfoLoadPage.getUrl());
			dbParam.setUsername(dbInfoLoadPage.getUsername());
			dbParam.setPassword(dbInfoLoadPage.getPassword());
			dbParam.setSrcDir(Platform.getLocation().toString()
					+ destSelectPage.getDestDir());
			dbParam.setPackageName(destSelectPage.getPackageName());
			dbParam.setDaoType(typeSelectPage.getDaoType());
			dbParam.setDbType(typeSelectPage.getDbType());
			localConfig = new LocalConfig();
			localConfig.setSourceType(type);
			localConfig.setDbParam(dbParam);
		}
		logger.debug("localConfig: {}", localConfig);

		// 进度条
		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					if (localConfig.getSourceType() == SourceType.db) {
						createFromDb(monitor);
					}
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		// 进度条执行
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
			dialog.run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * 根据数据库生成相应的文件
	 */
	private void createFromDb(IProgressMonitor monitor) throws CoreException {
		CreateFromDbParam dbParam = localConfig.getDbParam();

		Map<String, String> map = new HashMap<String, String>();
		map.put(KEY_LOCAL_CONFIG, JsonUtils.toJson(localConfig));

		// -------------------------
		// 一、获取数据库信息
		// -------------------------
		monitor.beginTask("（1/4）获取数据库信息", 2);
		// 1.1 连接数据库
		monitor.setTaskName("（1/4）获取数据库信息：连接数据库");
		Connection conn = ConnectionUtils.getConnection(dbParam.getJarDir(),
				dbParam.getDriverName(), dbParam.getUrl(),
				dbParam.getUsername(), dbParam.getPassword());
		if (conn == null) {
			IStatus status = new Status(IStatus.ERROR, "codecreator-plugin",
					"数据库连接出错");
			throw new CoreException(status);
		}
		monitor.worked(1);
		// 1.2 获取库表信息
		monitor.setTaskName("（1/4）获取数据库信息：获取库表信息");
		List<String> tableList = ConnectionUtils.listTables(conn);
		monitor.worked(1);
		logger.debug("tableList: {}", tableList);

		// -------------------------
		// 二、基础代码生成
		// -------------------------
		monitor.beginTask("（2/4）基础代码生成", 2);
		// 2.1 生成基础DAO
		monitor.setTaskName("（2/4）基础代码生成：生成"
				+ NameBuilder.buildBasicDaoFileName());
		createBasicDao(dbParam);
		monitor.worked(1);
		// 2.2 生成基础Entity类
		monitor.setTaskName("（2/4）基础代码生成：生成"
				+ NameBuilder.buildBasicEntityFileName());
		createBasicEntity(dbParam);
		monitor.worked(1);

		// ------------------------------
		// 三、基础代码生成
		// ------------------------------
		monitor.beginTask("（3/4）基础代码生成", tableList.size() * 3);
		for (String tableName : tableList) {
			String fileName = null;
			DefaultResult result = null;

			// 连接数据库
			monitor.setTaskName("（3/4）基础代码生成：获取" + tableName + "的表信息");
			TableInfo tableInfo = TableUtils.readTable(tableName, conn);
			map.put(tableName, JsonUtils.toJson(tableInfo));
			monitor.worked(1);

			// 生成实体类
			fileName = NameBuilder.buildEntityFileName(tableName);
			monitor.setTaskName("（3/4）基础代码生成：生成" + fileName);
			result = createEntity(dbParam, tableInfo);
			logger.debug("{} entity result: {}", tableName, result);
			if (result != null) {
				if (result.getCode() == DefaultResult.ERROR_CODE) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", result.getMsg());
					throw new CoreException(status);
				}
				try {
					SourceFileUtils.writeSourceFile(dbParam.getSrcDir(),
							dbParam.getPackageName() + ".dao.entity", fileName,
							result.getResultStr());
				} catch (IOException e) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", fileName + "文件生成错误");
					throw new CoreException(status);
				}
			}
			monitor.worked(1);

			// 生成dao
			fileName = NameBuilder.buildDaoFileName(tableName);
			monitor.setTaskName("（3/4）基础代码生成：生成" + fileName);
			if (!SourceFileUtils.exists(dbParam.getSrcDir(),
					dbParam.getPackageName() + ".dao.inter", fileName)) { // 要生成的实体类文件不存在
				result = HttpDaoByClientBuilder.buildDao(
						dbParam.getPackageName(), tableInfo);
			} else {
				result = null;
			}
			logger.debug("{} dao result: {}", tableName, result);
			if (result != null) {
				if (result.getCode() == DefaultResult.ERROR_CODE) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", result.getMsg());
					throw new CoreException(status);
				}
				try {
					fileName = NameBuilder.buildDaoFileName(tableName);
					SourceFileUtils.writeSourceFile(dbParam.getSrcDir(),
							dbParam.getPackageName() + ".dao.inter", fileName,
							result.getResultStr());
				} catch (IOException e) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", fileName + "文件生成错误");
					throw new CoreException(status);
				}
			}
			monitor.worked(1);

			// 生成mapper
			fileName = NameBuilder.buildDaoMapperFileName(tableName);
			monitor.setTaskName("（3/4）基础代码生成：生成" + fileName);
			result = createMapperXml(dbParam, tableInfo);
			logger.debug("{} mapperxml result: {}", tableName, result);
			if (result != null) {
				if (result.getCode() == DefaultResult.ERROR_CODE) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", result.getMsg());
					throw new CoreException(status);
				}
				try {
					fileName = NameBuilder.buildDaoMapperFileName(tableName);
					SourceFileUtils.writeSourceFile(dbParam.getSrcDir(),
							dbParam.getPackageName() + ".dao.inter", fileName,
							result.getResultStr());
				} catch (IOException e) {
					IStatus status = new Status(IStatus.ERROR,
							"codecreator-plugin", fileName + "文件生成错误");
					throw new CoreException(status);
				}
			}
			monitor.worked(1);
		}

		// 本次配置记录到文件中
		monitor.beginTask("（4/4）写入本次配置文件", 1);
		logger.debug("config map: {}", map);
		writeConfig(map);
		monitor.worked(1);

		// 刷新
		project.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);

	}

	/**
	 * 生成实体类内容
	 * 
	 * @param dbParam
	 * @param tableInfo
	 * @return
	 */
	private DefaultResult createEntity(CreateFromDbParam dbParam,
			TableInfo tableInfo) {
		DefaultResult result = null;
		String tableName = tableInfo.getTableName();
		String fileName = NameBuilder.buildEntityFileName(tableName);
		String source = null;
		boolean fileExists = SourceFileUtils.exists(dbParam.getSrcDir(),
				dbParam.getPackageName() + ".dao.entity", fileName);
		if (fileExists) {
			try {
				source = SourceFileUtils.readSourceFile(dbParam.getSrcDir(),
						dbParam.getPackageName() + ".dao.entity", fileName);
			} catch (IOException e) {
				logger.error("error: {}\n{}\n", e.getMessage(),
						e.getStackTrace());
			}
		}

		if (!fileExists
				|| !JsonUtils.toJson(tableInfo).equals(
						properties.get(tableName))) {
			result = HttpEntityByClientBuilder.buildEntity(dbParam.getDbType(),
					dbParam.getDaoType(), dbParam.getPackageName(), tableInfo,
					source);
		}

		return result;
	}

	/**
	 * 生成mapperXml内容
	 * 
	 * @param dbParam
	 * @param tableInfo
	 * @return
	 */
	private DefaultResult createMapperXml(CreateFromDbParam dbParam,
			TableInfo tableInfo) {
		DefaultResult result = null;
		String tableName = tableInfo.getTableName();
		String fileName = NameBuilder.buildDaoMapperFileName(tableName);
		String source = null;
		boolean fileExists = SourceFileUtils.exists(dbParam.getSrcDir(),
				dbParam.getPackageName() + ".dao.inter", fileName);
		if (fileExists) {
			try {
				source = SourceFileUtils.readSourceFile(dbParam.getSrcDir(),
						dbParam.getPackageName() + ".dao.inter", fileName);
			} catch (IOException e) {
				logger.error("error: {}\n{}\n", e.getMessage(),
						e.getStackTrace());
			}
		}

		if (!fileExists
				|| !JsonUtils.toJson(tableInfo).equals(
						properties.get(tableName))) {
			result = HttpMapperXmlByClientBuilder.buildMapperXml(
					dbParam.getDbType(), dbParam.getPackageName(), tableInfo,
					source);
		}

		return result;

	}

	/**
	 * 生成_Entity.java文件
	 * 
	 * @param dbParam
	 * @throws CoreException
	 */
	private void createBasicEntity(CreateFromDbParam dbParam)
			throws CoreException {
		if (!SourceFileUtils.exists(dbParam.getSrcDir(),
				dbParam.getPackageName() + ".dao.entity",
				NameBuilder.buildBasicEntityFileName())) {
			DefaultResult result = HttpEntityByClientBuilder
					.buildBasicEntity(dbParam.getPackageName());
			if (result.getCode() == DefaultResult.ERROR_CODE) {
				IStatus status = new Status(IStatus.ERROR,
						"codecreator-plugin", result.getMsg());
				throw new CoreException(status);
			}
			try {
				SourceFileUtils.writeSourceFile(dbParam.getSrcDir(),
						dbParam.getPackageName() + ".dao.entity",
						NameBuilder.buildBasicEntityFileName(),
						result.getResultStr());
			} catch (IOException e) {
				IStatus status = new Status(IStatus.ERROR,
						"codecreator-plugin", "生成基础实体类文件错误");
				throw new CoreException(status);
			}
		}
	}

	/**
	 * 生成基础_DAO.java文件
	 * 
	 * @param dbParam
	 * @throws CoreException
	 */
	private void createBasicDao(CreateFromDbParam dbParam) throws CoreException {
		if (!SourceFileUtils.exists(dbParam.getSrcDir(),
				dbParam.getPackageName() + ".dao.inter",
				NameBuilder.buildBasicDaoFileName())) {
			DefaultResult result = HttpDaoByClientBuilder.buildBasicDao(dbParam
					.getPackageName());
			if (result.getCode() == DefaultResult.ERROR_CODE) {
				IStatus status = new Status(IStatus.ERROR,
						"codecreator-plugin", result.getMsg());
				throw new CoreException(status);
			}
			try {
				SourceFileUtils.writeSourceFile(dbParam.getSrcDir(),
						dbParam.getPackageName() + ".dao.inter",
						NameBuilder.buildBasicDaoFileName(),
						result.getResultStr());
			} catch (IOException e) {
				IStatus status = new Status(IStatus.ERROR,
						"codecreator-plugin", "生成基础DAO文件错误");
				throw new CoreException(status);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		IWizardPage page = getContainer().getCurrentPage();
		if (page instanceof SourceSelectPage) {
			return false;
		}
		if (page instanceof TypeSelectPage) {
			return false;
		}
		if (page instanceof DbInfoLoadPage) {
			return false;
		}
		return true;
	}

	/**
	 * 将读取到的文件写入到配置文件中
	 * 
	 * @throws CoreException
	 */
	private void writeConfig(Map<String, String> map) throws CoreException {
		StringBuffer sb = new StringBuffer();
		for (String key : map.keySet()) {
			sb.append(key + "=" + map.get(key) + "\n");
		}
		try {
			IFile file = project.getProject().getFile(CONFIG_FILE_NAME);
			if (!file.exists()) {
				file.create(
						new ByteArrayInputStream(sb.toString()
								.getBytes("UTF-8")), false, null);
			} else {
				file.setContents(new ByteArrayInputStream(sb.toString()
						.getBytes("UTF-8")), false, false, null);
			}
		} catch (UnsupportedEncodingException e) {
			IStatus status = new Status(IStatus.ERROR, "codecreator-plugin",
					"本次配置保存错误");
			throw new CoreException(status);
		}
	}

	/**
	 * 读取配置文件
	 * 
	 * @return
	 */
	private Properties readConfig() {
		IFile file = project.getProject().getFile(CONFIG_FILE_NAME);
		Properties properties = new Properties();
		if (file.exists()) {
			try {
				properties.load(file.getContents());
			} catch (IOException e) {
				logger.error("error: {}\n{}\n", e.getMessage(),
						e.getStackTrace());
			} catch (CoreException e) {
				logger.error("error: {}\n{}\n", e.getMessage(),
						e.getStackTrace());
			}
		}
		return properties;
	}

}
