/**
 * #SysCodeText.java -- 2014-12-07
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * sys_code_text表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-12-07
 */
public class SysCodeText extends _Entity {
	private static final long serialVersionUID = 6064505944146218754L;
	public static String TABLE_NAME = "sys_code_text";
	
	//autocreate_start
	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_TABLE_NAME = "table_name";
	public static String FIELD_TABLE_NAME_ASC = "table_name asc";
	public static String FIELD_TABLE_NAME_DESC = "table_name desc";
	public static String FIELD_COLUMN_NAME = "column_name";
	public static String FIELD_COLUMN_NAME_ASC = "column_name asc";
	public static String FIELD_COLUMN_NAME_DESC = "column_name desc";
	public static String FIELD_CODE = "code";
	public static String FIELD_CODE_ASC = "code asc";
	public static String FIELD_CODE_DESC = "code desc";
	public static String FIELD_TEXT = "text";
	public static String FIELD_TEXT_ASC = "text asc";
	public static String FIELD_TEXT_DESC = "text desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 table_name
	private String tableName;

	// 对应字段 column_name
	private String columnName;

	// 对应字段 code
	private Integer code;

	// 对应字段 text
	private String text;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取tableName属性值，对应于table_name字段
	 * @return tableName值
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 获取columnName属性值，对应于column_name字段
	 * @return columnName值
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * 获取code属性值，对应于code字段
	 * @return code值
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 获取text属性值，对应于text字段
	 * @return text值
	 */
	public String getText() {
		return text;
	}



	/**
	 * 设置id属性值，对应于id字段
	 * @param id
	 *            要设置的id属性值
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 设置tableName属性值，对应于table_name字段
	 * @param tableName
	 *            要设置的tableName属性值
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 设置columnName属性值，对应于column_name字段
	 * @param columnName
	 *            要设置的columnName属性值
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 设置code属性值，对应于code字段
	 * @param code
	 *            要设置的code属性值
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 设置text属性值，对应于text字段
	 * @param text
	 *            要设置的text属性值
	 */
	public void setText(String text) {
		this.text = text;
	}


	//autocreate_end

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
