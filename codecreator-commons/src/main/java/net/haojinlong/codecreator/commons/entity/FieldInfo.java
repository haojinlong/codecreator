/**
 * # FieldInfo.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.commons.entity;

import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 映射类的属性名称
 * 
 * @author 郝金隆
 * @since 2014年10月31日
 *
 */
public class FieldInfo {
	static Logger logger = LoggerFactory.getLogger(FieldInfo.class);
	// 是否为空
	private boolean isBoolean;
	// 是否为java.sql.Date，需特殊处理
	private boolean isSqlDate;
	// 对应的java类
	private String javaType;
	// 是否需要引入此java类
	private boolean needImport;
	// 短类型
	private String shortType;

	/**
	 * @return the isSqlDate
	 */
	public boolean isSqlDate() {
		return isSqlDate;
	}

	/**
	 * @param isSqlDate
	 *            the isSqlDate to set
	 */
	public void setSqlDate(boolean isSqlDate) {
		this.isSqlDate = isSqlDate;
	}

	/**
	 * @return the isBoolean
	 */
	public boolean isBoolean() {
		return isBoolean;
	}

	/**
	 * @param isBoolean
	 *            the isBoolean to set
	 */
	public void setBoolean(boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	/**
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}

	/**
	 * @param javaType
	 *            the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * @return the needImport
	 */
	public boolean isNeedImport() {
		return needImport;
	}

	/**
	 * @param needImport
	 *            the needImport to set
	 */
	public void setNeedImport(boolean needImport) {
		this.needImport = needImport;
	}

	/**
	 * @return the shortType
	 */
	public String getShortType() {
		return shortType;
	}

	/**
	 * @param shortType
	 *            the shortType to set
	 */
	public void setShortType(String shortType) {
		this.shortType = shortType;
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
