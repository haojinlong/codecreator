/**
 * # BasicEntityParam.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.commons.http;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
public class DefaultParam {
	static Logger logger = LoggerFactory.getLogger(DefaultParam.class);

	private DbType dbType;
	private DaoType daoType;
	private String packageName;
	private String tableInfoJson;
	private String source;

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
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @return the tableInfoJson
	 */
	public String getTableInfoJson() {
		return tableInfoJson;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
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

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @param tableInfoJson
	 *            the tableInfoJson to set
	 */
	public void setTableInfoJson(String tableInfoJson) {
		this.tableInfoJson = tableInfoJson;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
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
