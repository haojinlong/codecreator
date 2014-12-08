/**
 * # CreateFromDbParam.java -- (2014年11月4日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.common;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月4日
 *
 */
public class CreateFromDbParam {
	static Logger logger = LoggerFactory.getLogger(CreateFromDbParam.class);
	String jarDir;
	String driverName;
	String url;
	String username;
	String password;
	String srcDir;
	String packageName;

	DbType dbType;
	DaoType daoType;

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

	/**
	 * @return the srcDir
	 */
	public String getSrcDir() {
		return srcDir;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param jarDir
	 *            the jarDir to set
	 */
	public void setJarDir(String jarDir) {
		this.jarDir = jarDir;
	}

	/**
	 * @param driverName
	 *            the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param srcDir
	 *            the srcDir to set
	 */
	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the dbType
	 */
	public DbType getDbType() {
		return dbType;
	}

	/**
	 * @return the daoType
	 */
	public DaoType getDaoType() {
		return daoType;
	}

	/**
	 * @param dbType
	 *            the dbType to set
	 */
	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	/**
	 * @param daoType
	 *            the daoType to set
	 */
	public void setDaoType(DaoType daoType) {
		this.daoType = daoType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
