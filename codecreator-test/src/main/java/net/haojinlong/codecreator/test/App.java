package net.haojinlong.codecreator.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.core.builder.DaoBuilder;
import net.haojinlong.codecreator.core.builder.DefaultDaoBuilder;
import net.haojinlong.codecreator.core.builder.DefaultEntityBuilder;
import net.haojinlong.codecreator.core.builder.DefaultMapperXmlBuilder;
import net.haojinlong.codecreator.core.builder.EntityBuilder;
import net.haojinlong.codecreator.core.builder.MapperXmlBuilder;
import net.haojinlong.codecreator.http.HttpEntityByClientBuilder;
import net.haojinlong.codecreator.utils.ConnectionUtils;
import net.haojinlong.codecreator.utils.SourceFileUtils;
import net.haojinlong.codecreator.utils.TableUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, SQLException {
		String jar = "/home/haojinlong/.m2/repository/mysql/mysql-connector-java/5.1.33/mysql-connector-java-5.1.33.jar";
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "root";
		String srcDir = "/home/haojinlong/2-dev/1-javaee/test-mybatis/src/main/java";
		String packageName = "net.haojinlong.mybatis";
		Connection conn = ConnectionUtils.getConnection(jar, driverName, url,
				username, password);
		List<String> tableList = ConnectionUtils.listTables(conn);
		DaoBuilder daoBuilder = new DefaultDaoBuilder();
		EntityBuilder entityBuilder = new DefaultEntityBuilder(DbType.mysql,
				DaoType.mybatis);
		MapperXmlBuilder mapperXmlBuilder = new DefaultMapperXmlBuilder(
				DbType.mysql);

		// 创建基础Dao
		if (!SourceFileUtils.exists(srcDir, packageName + ".dao.inter",
				NameBuilder.buildBasicDaoFileName())) {
			SourceFileUtils.writeSourceFile(srcDir, packageName + ".dao.inter",
					NameBuilder.buildBasicDaoFileName(),
					daoBuilder.buildBasicDao(packageName));
		}

		// 创建基础Entity
		if (!SourceFileUtils.exists(srcDir, packageName + ".dao.entity",
				NameBuilder.buildBasicEntityFileName())) {
			SourceFileUtils.writeSourceFile(srcDir,
					packageName + ".dao.entity",
					NameBuilder.buildBasicEntityFileName(),
					entityBuilder.buildBasicEntity(packageName));
		}

		for (String tableName : tableList) {
			TableInfo tableInfo = TableUtils.readTable(tableName, conn);
			String entityFileName = NameBuilder.buildEntityFileName(tableName);
			String daoFileName = NameBuilder.buildDaoFileName(tableName);
			String mapperFileName = NameBuilder
					.buildDaoMapperFileName(tableName);

			// 创建entity
			if (SourceFileUtils.exists(srcDir, packageName + ".dao.entity",
					entityFileName)) { // 已存在，替换原有内容
				String source = SourceFileUtils.readSourceFile(srcDir,
						packageName + ".dao.entity", entityFileName);
				String content = HttpEntityByClientBuilder.buildEntity(
						DbType.mysql, DaoType.mybatis, packageName, tableInfo,
						source).getResultStr();
				// String content = entityBuilder.buildEntity(packageName,
				// tableInfo, source);
				if (content != null) {
					SourceFileUtils.writeSourceFile(srcDir, packageName
							+ ".dao.entity", entityFileName, content);
				}
				else{
					System.out.println("chucuo");
				}
			} else { // 不存在，则新建
				String content = HttpEntityByClientBuilder.buildEntity(
						DbType.mysql, DaoType.mybatis, packageName, tableInfo,
						null).getResultStr();
				// String content = entityBuilder.buildEntity(packageName,
				// tableInfo);
				if (content != null) {
					SourceFileUtils.writeSourceFile(srcDir, packageName
							+ ".dao.entity", entityFileName, content);
				}
			}

			// 创建dao文件
			if (!SourceFileUtils.exists(srcDir, packageName + ".dao.inter",
					daoFileName)) {
				String content = daoBuilder.buildDao(packageName, tableInfo);
				SourceFileUtils.writeSourceFile(srcDir, packageName
						+ ".dao.inter", daoFileName, content);
			}

			// 创建dao mapper文件
			if (SourceFileUtils.exists(srcDir, packageName + ".dao.inter",
					mapperFileName)) {
				String source = SourceFileUtils.readSourceFile(srcDir,
						packageName + ".dao.inter", mapperFileName);
				String content = mapperXmlBuilder.buildMapper(packageName,
						tableInfo, source);
				SourceFileUtils.writeSourceFile(srcDir, packageName
						+ ".dao.inter", mapperFileName, content);
			} else {
				String content = mapperXmlBuilder.buildMapper(packageName,
						tableInfo);
				SourceFileUtils.writeSourceFile(srcDir, packageName
						+ ".dao.inter", mapperFileName, content);
			}

		}

		conn.close();

	}
}
