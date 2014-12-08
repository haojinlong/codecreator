/**
 * # ConnectionUtils.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库连接工具类
 * 
 * @author 郝金隆
 * @since 2014年10月31日
 *
 */
public class ConnectionUtils {
	static Logger logger = LoggerFactory.getLogger(ConnectionUtils.class);

	/**
	 * 获取数据库连接
	 * 
	 * @param driverJars
	 *            驱动程序包的绝对地址
	 * @param driverName
	 *            驱动程序类全名
	 * @param url
	 *            连接地址
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 数据库连接
	 */
	public static Connection getConnection(String jar, String driverName,
			String url, String userName, String password) {

		try {
			URL urls[];
			urls = new URL[] { new File(jar).toURI().toURL() };
			URLClassLoader loader = new URLClassLoader(urls);
			Class<?> clazz = loader.loadClass(driverName);
			Driver driver = (Driver) clazz.newInstance();
			Properties p = new Properties();
			p.put("user", userName);
			p.put("password", password);
			Connection con = driver.connect(url, p);
			// loader.close();
			return new MyConnection(loader, con);
		} catch (MalformedURLException e) {
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		} catch (SQLException e) {
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		} catch (ClassNotFoundException e) {
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		} catch (InstantiationException e) {
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		} catch (IllegalAccessException e) {
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		}

		return null;

		// URLClassLoader classLoader = (URLClassLoader) ClassLoader
		// .getSystemClassLoader();
		//
		// // URLClassLoader classLoader = (URLClassLoader)
		// Thread.currentThread()
		// // .getContextClassLoader();
		// try {
		// URL urls = new File(jar).toURI().toURL();
		// Class<URLClassLoader> sysclass = URLClassLoader.class;
		// Method method = sysclass.getDeclaredMethod("addURL",
		// new Class[] { URL.class });
		// method.setAccessible(true);
		// method.invoke(classLoader, urls);
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		//
		// try {
		// Class.forName(driverName);
		// Connection con = DriverManager.getConnection(url, userName,
		// password);
		// return con;
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	/**
	 * 获取数据库中所有的表名
	 * 
	 * @param conn
	 *            数据库连接
	 * @return 表名列表
	 */
	public static List<String> listTables(Connection conn) {
		List<String> result = new ArrayList<String>();
		try {
			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			while (rs.next()) {
				result.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
