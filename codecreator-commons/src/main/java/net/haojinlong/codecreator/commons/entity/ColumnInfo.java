/**
 * # ColumnType.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.commons.entity;

import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据表列类型
 * 
 * @author 郝金隆
 * @since 2014年10月31日
 */
public class ColumnInfo {
	static Logger logger = LoggerFactory.getLogger(ColumnInfo.class);
	// 列名
	private String columnName;
	// 列类型
	private String columnType;
	// 列长
	private int length;
	// 列备注
	private String columnComment;
	// 自动获取的列对应的Java类
	private String defaultJavaType;
	// 对应的映射类属性名
	private String fieldName;
	// 对应的映射类的属性
	private FieldInfo fieldInfo;
	// 是否为主键
	private boolean primaryKey;

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}

	/**
	 * @param columnType
	 *            the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the columnComment
	 */
	public String getColumnComment() {
		return columnComment;
	}

	/**
	 * @param columnComment
	 *            the columnComment to set
	 */
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the fieldInfo
	 */
	public FieldInfo getFieldInfo() {
		return fieldInfo;
	}

	/**
	 * @param fieldInfo
	 *            the fieldInfo to set
	 */
	public void setFieldInfo(FieldInfo fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	/**
	 * @return the defaultJavaType
	 */
	public String getDefaultJavaType() {
		return defaultJavaType;
	}

	/**
	 * @param defaultJavaType
	 *            the defaultJavaType to set
	 */
	public void setDefaultJavaType(String defaultJavaType) {
		this.defaultJavaType = defaultJavaType;
	}

	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void initFieldInfo() {
		fieldInfo = new FieldInfo();
		if (defaultJavaType != null) {
			String shortType = defaultJavaType.substring(defaultJavaType
					.lastIndexOf('.') + 1);
			// shortType
			fieldInfo.setShortType(shortType);
			// needImport
			if (defaultJavaType.indexOf('.') > 0
					&& !defaultJavaType.startsWith("java.lang")) {
				fieldInfo.setNeedImport(true);
			} else {
				fieldInfo.setNeedImport(false);
			}
			// javaType
			fieldInfo.setJavaType(defaultJavaType);

			// boolean
			if (shortType.equals("Boolean")) {
				fieldInfo.setBoolean(true);
			} else {
				fieldInfo.setBoolean(false);
			}

			// 处理BLOB
			if (defaultJavaType.equals("[B")) {
				fieldInfo.setJavaType("byte[]");
				fieldInfo.setShortType("byte[]");
			}

		}
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
