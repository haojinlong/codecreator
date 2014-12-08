/**
 * #ColumnUpdate.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_column_update表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class ColumnUpdate extends _Entity {
	private static final long serialVersionUID = 7215998050312794561L;
	public static String TABLE_NAME = "t_column_update";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_TABLE_ID = "table_id";
	public static String FIELD_TABLE_ID_ASC = "table_id asc";
	public static String FIELD_TABLE_ID_DESC = "table_id desc";
	public static String FIELD_TABLE_VERSION = "table_version";
	public static String FIELD_TABLE_VERSION_ASC = "table_version asc";
	public static String FIELD_TABLE_VERSION_DESC = "table_version desc";
	public static String FIELD_OLD_COLUMN_NAME = "old_column_name";
	public static String FIELD_OLD_COLUMN_NAME_ASC = "old_column_name asc";
	public static String FIELD_OLD_COLUMN_NAME_DESC = "old_column_name desc";
	public static String FIELD_NEW_COLUMN_NAME = "new_column_name";
	public static String FIELD_NEW_COLUMN_NAME_ASC = "new_column_name asc";
	public static String FIELD_NEW_COLUMN_NAME_DESC = "new_column_name desc";
	public static String FIELD_TYPE_CODE = "type_code";
	public static String FIELD_TYPE_CODE_ASC = "type_code asc";
	public static String FIELD_TYPE_CODE_DESC = "type_code desc";
	public static String FIELD_LENGTH = "length";
	public static String FIELD_LENGTH_ASC = "length asc";
	public static String FIELD_LENGTH_DESC = "length desc";
	public static String FIELD_DESCRIPTION = "description";
	public static String FIELD_DESCRIPTION_ASC = "description asc";
	public static String FIELD_DESCRIPTION_DESC = "description desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 table_id
	private Integer tableId;

	// 对应字段 table_version
	private Integer tableVersion;

	// 对应字段 old_column_name
	private String oldColumnName;

	// 对应字段 new_column_name
	private String newColumnName;

	// 对应字段 type_code
	private Integer typeCode;

	// 对应字段 length
	private Integer length;

	// 对应字段 description
	private String description;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取tableId属性值，对应于table_id字段
	 * @return tableId值
	 */
	public Integer getTableId() {
		return tableId;
	}

	/**
	 * 获取tableVersion属性值，对应于table_version字段
	 * @return tableVersion值
	 */
	public Integer getTableVersion() {
		return tableVersion;
	}

	/**
	 * 获取oldColumnName属性值，对应于old_column_name字段
	 * @return oldColumnName值
	 */
	public String getOldColumnName() {
		return oldColumnName;
	}

	/**
	 * 获取newColumnName属性值，对应于new_column_name字段
	 * @return newColumnName值
	 */
	public String getNewColumnName() {
		return newColumnName;
	}

	/**
	 * 获取typeCode属性值，对应于type_code字段
	 * @return typeCode值
	 */
	public Integer getTypeCode() {
		return typeCode;
	}

	/**
	 * 获取length属性值，对应于length字段
	 * @return length值
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * 获取description属性值，对应于description字段
	 * @return description值
	 */
	public String getDescription() {
		return description;
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
	 * 设置tableId属性值，对应于table_id字段
	 * @param tableId
	 *            要设置的tableId属性值
	 */
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	/**
	 * 设置tableVersion属性值，对应于table_version字段
	 * @param tableVersion
	 *            要设置的tableVersion属性值
	 */
	public void setTableVersion(Integer tableVersion) {
		this.tableVersion = tableVersion;
	}

	/**
	 * 设置oldColumnName属性值，对应于old_column_name字段
	 * @param oldColumnName
	 *            要设置的oldColumnName属性值
	 */
	public void setOldColumnName(String oldColumnName) {
		this.oldColumnName = oldColumnName;
	}

	/**
	 * 设置newColumnName属性值，对应于new_column_name字段
	 * @param newColumnName
	 *            要设置的newColumnName属性值
	 */
	public void setNewColumnName(String newColumnName) {
		this.newColumnName = newColumnName;
	}

	/**
	 * 设置typeCode属性值，对应于type_code字段
	 * @param typeCode
	 *            要设置的typeCode属性值
	 */
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 设置length属性值，对应于length字段
	 * @param length
	 *            要设置的length属性值
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 设置description属性值，对应于description字段
	 * @param description
	 *            要设置的description属性值
	 */
	public void setDescription(String description) {
		this.description = description;
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
