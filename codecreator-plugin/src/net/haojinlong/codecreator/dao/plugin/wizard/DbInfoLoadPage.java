/**
 * # DbInfoLoadPage.java -- (2014年11月4日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import net.haojinlong.codecreator.commons.DbType;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月4日
 *
 */
public class DbInfoLoadPage extends WizardPage {
	static Logger logger = LoggerFactory.getLogger(DbInfoLoadPage.class);

	private String jarDir;
	private String driverName;
	private String url;
	private String password;
	private String username;

	private Text jarDirText;
	private Text driverNameText;
	private Text urlText;
	private Text usernameText;
	private Text passwordText;

	private FileDialog fileDialog;

	public DbInfoLoadPage() {
		super("dbInfoLoad");
		setTitle("创建数据库链接");
		setDescription("请选择本地数据库驱动包，设置jdbc驱动、URL地址、用户名和密码信息");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		// 总容器
		Composite container = new Composite(parent, SWT.NULL);
		Layout layout = new GridLayout(1, false);
		container.setLayout(layout);

		// 加框，分行
		Composite dbInfoContainer = new Composite(container, SWT.BORDER);
		Layout dbInfoLayout = new GridLayout(3, false);
		dbInfoContainer.setLayout(dbInfoLayout);
		GridData dbGridData = new GridData(GridData.FILL_HORIZONTAL);
		dbInfoContainer.setLayoutData(dbGridData);

		// TextGridData
		GridData textGridData = new GridData(GridData.FILL_HORIZONTAL);

		// jarDir
		Label jarLabel = new Label(dbInfoContainer, SWT.NULL);
		jarLabel.setText("驱动程序：");
		jarDirText = new Text(dbInfoContainer, SWT.BORDER | SWT.SINGLE);
		jarDirText.setLayoutData(textGridData);
		jarDirText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				textChange();
			}
		});
		Button fileButton = new Button(dbInfoContainer, SWT.PUSH);
		fileButton.setText("选择");
		fileButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chooseFile();
			}
		});

		// driverName
		Label driverNameLabel = new Label(dbInfoContainer, SWT.NULL);
		driverNameLabel.setText("DriverName：");
		driverNameText = new Text(dbInfoContainer, SWT.BORDER | SWT.SINGLE);
		driverNameText.setLayoutData(textGridData);
		driverNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textChange();
			}
		});
		new Label(dbInfoContainer, SWT.NULL);

		// url
		Label urlLabel = new Label(dbInfoContainer, SWT.NULL);
		urlLabel.setText("数据库链接地址：");
		urlText = new Text(dbInfoContainer, SWT.BORDER | SWT.SINGLE);
		urlText.setLayoutData(textGridData);
		urlText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textChange();
			}
		});
		new Label(dbInfoContainer, SWT.NULL);

		// username
		Label usernameLabel = new Label(dbInfoContainer, SWT.NULL);
		usernameLabel.setText("用户名：");
		usernameText = new Text(dbInfoContainer, SWT.BORDER | SWT.SINGLE);
		usernameText.setLayoutData(textGridData);
		usernameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textChange();
			}
		});
		new Label(dbInfoContainer, SWT.NULL);

		// password
		Label passwordLabel = new Label(dbInfoContainer, SWT.NULL);
		passwordLabel.setText("密码：");
		passwordText = new Text(dbInfoContainer, SWT.BORDER | SWT.SINGLE);
		passwordText.setLayoutData(textGridData);
		passwordText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textChange();
			}
		});
		new Label(dbInfoContainer, SWT.NULL);

		// 初始化dialog
		fileDialog = new FileDialog(getShell());
		fileDialog.setFilterExtensions(new String[] { "*.jar" });

		init();
		textChange();
		setControl(container);
	}

	// 初始化内容
	private void init() {
		jarDirText.setEnabled(false);
	}

	// 选择文件
	private void chooseFile() {
		jarDir = fileDialog.open();
		if (jarDir != null) {
			jarDirText.setText(jarDir.replace('\\', '/'));
			textChange();
		}
	}

	/**
	 * 根据上一页选择的数据库类型初始化文本框
	 * 
	 * @param dbType
	 *            数据库类型
	 */
	public void setDbType(DbType dbType) {
		if (dbType == DbType.mysql) {
			driverNameText.setText("com.mysql.jdbc.Driver");
			urlText.setText("jdbc:mysql://localhost:3306/test");
			usernameText.setText("root");
			passwordText.setText("root");
		} else {
			driverNameText.setText("");
			urlText.setText("");
			usernameText.setText("");
			passwordText.setText("");
		}
	}

	/**
	 * 确定所有的内容均已被输入
	 */
	private void textChange() {
		jarDir = jarDirText.getText();
		driverName = driverNameText.getText();
		url = urlText.getText();
		username = usernameText.getText();
		password = passwordText.getText();
		if (jarDir == null || !jarDir.endsWith(".jar")) {
			updateStatus("请选择正确的驱动程序jar包");
			return;
		}
		if (driverName == null || driverName.length() == 0) {
			updateStatus("请输入正确的DriverName");
			return;
		}
		if (url == null || url.length() == 0) {
			updateStatus("请输入数据库链接地址");
			return;
		}
		if (username == null || username.length() == 0) {
			updateStatus("请输入用户名");
			return;
		}
		if (password == null || password.length() == 0) {
			updateStatus("请输入密码");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String msg) {
		setErrorMessage(msg);
		setPageComplete(msg == null);
	}

	/**
	 * @return the jarDir
	 */
	public String getJarDir() {
		return jarDir;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
