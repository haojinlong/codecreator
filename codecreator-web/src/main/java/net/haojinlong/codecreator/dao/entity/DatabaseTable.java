/**
 * #DatabaseTable.java -- 2014-12-08
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_database_table表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-12-08
 */
public class DatabaseTable extends _Entity {
	private static final long serialVersionUID = 6784221333711189907L;
	public static String TABLE_NAME = "t_database_table";
	
	//autocreate_start
	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_DB_ID = "db_id";
	public static String FIELD_DB_ID_ASC = "db_id asc";
	public static String FIELD_DB_ID_DESC = "db_id desc";
	public static String FIELD_DB_VERSION = "db_version";
	public static String FIELD_DB_VERSION_ASC = "db_version asc";
	public static String FIELD_DB_VERSION_DESC = "db_version desc";
	public static String FIELD_TABLE_ID = "table_id";
	public static String FIELD_TABLE_ID_ASC = "table_id asc";
	public static String FIELD_TABLE_ID_DESC = "table_id desc";
	public static String FIELD_IS_NEW = "is_new";
	public static String FIELD_IS_NEW_ASC = "is_new asc";
	public static String FIELD_IS_NEW_DESC = "is_new desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 db_id
	private Integer dbId;

	// 对应字段 db_version
	private Integer dbVersion;

	// 对应字段 table_id
	private Integer tableId;

	// 对应字段 is_new
	private Boolean isNew;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取dbId属性值，对应于db_id字段
	 * @return dbId值
	 */
	public Integer getDbId() {
		return dbId;
	}

	/**
	 * 获取dbVersion属性值，对应于db_version字段
	 * @return dbVersion值
	 */
	public Integer getDbVersion() {
		return dbVersion;
	}

	/**
	 * 获取tableId属性值，对应于table_id字段
	 * @return tableId值
	 */
	public Integer getTableId() {
		return tableId;
	}

	/**
	 * 获取isNew属性值，对应于is_new字段
	 * @return isNew值
	 */
	public Boolean getIsNew() {
		return isNew;
	}
	
	
	/**
	 * 获取isNew属性值，对应于is_new字段
	 * @return isNew值
	 */
	public boolean is_IsNew() {
		return isNew;
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
	 * 设置dbId属性值，对应于db_id字段
	 * @param dbId
	 *            要设置的dbId属性值
	 */
	public void setDbId(Integer dbId) {
		this.dbId = dbId;
	}

	/**
	 * 设置dbVersion属性值，对应于db_version字段
	 * @param dbVersion
	 *            要设置的dbVersion属性值
	 */
	public void setDbVersion(Integer dbVersion) {
		this.dbVersion = dbVersion;
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
	 * 设置isNew属性值，对应于is_new字段
	 * @param isNew
	 *            要设置的isNew属性值
	 */
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
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
