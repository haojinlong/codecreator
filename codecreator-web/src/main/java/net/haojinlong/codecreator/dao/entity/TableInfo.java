/**
 * #TableInfo.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.Date;


/**
 * t_table_info表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class TableInfo extends _Entity {
	private static final long serialVersionUID = 707419605304437842L;
	public static String TABLE_NAME = "t_table_info";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_IDX = "idx";
	public static String FIELD_IDX_ASC = "idx asc";
	public static String FIELD_IDX_DESC = "idx desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_DESCRIPTION = "description";
	public static String FIELD_DESCRIPTION_ASC = "description asc";
	public static String FIELD_DESCRIPTION_DESC = "description desc";
	public static String FIELD_VERSION = "version";
	public static String FIELD_VERSION_ASC = "version asc";
	public static String FIELD_VERSION_DESC = "version desc";
	public static String FIELD_DB_ID = "db_id";
	public static String FIELD_DB_ID_ASC = "db_id asc";
	public static String FIELD_DB_ID_DESC = "db_id desc";
	public static String FIELD_LAST_COLUMN_IDX = "last_column_idx";
	public static String FIELD_LAST_COLUMN_IDX_ASC = "last_column_idx asc";
	public static String FIELD_LAST_COLUMN_IDX_DESC = "last_column_idx desc";
	public static String FIELD_LAST_INDEX_IDX = "last_index_idx";
	public static String FIELD_LAST_INDEX_IDX_ASC = "last_index_idx asc";
	public static String FIELD_LAST_INDEX_IDX_DESC = "last_index_idx desc";
	public static String FIELD_CREATE_TIME = "create_time";
	public static String FIELD_CREATE_TIME_ASC = "create_time asc";
	public static String FIELD_CREATE_TIME_DESC = "create_time desc";
	public static String FIELD_CREATE_DB_VERSION = "create_db_version";
	public static String FIELD_CREATE_DB_VERSION_ASC = "create_db_version asc";
	public static String FIELD_CREATE_DB_VERSION_DESC = "create_db_version desc";
	public static String FIELD_UPDATE_TIME = "update_time";
	public static String FIELD_UPDATE_TIME_ASC = "update_time asc";
	public static String FIELD_UPDATE_TIME_DESC = "update_time desc";
	public static String FIELD_UPDATE_DB_VERSION = "update_db_version";
	public static String FIELD_UPDATE_DB_VERSION_ASC = "update_db_version asc";
	public static String FIELD_UPDATE_DB_VERSION_DESC = "update_db_version desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 idx
	private Integer idx;

	// 对应字段 name
	private String name;

	// 对应字段 description
	private String description;

	// 对应字段 version
	private Integer version;

	// 对应字段 db_id
	private Integer dbId;

	// 对应字段 last_column_idx
	private Integer lastColumnIdx;

	// 对应字段 last_index_idx
	private Integer lastIndexIdx;

	// 对应字段 create_time
	private Date createTime;

	// 对应字段 create_db_version
	private Integer createDbVersion;

	// 对应字段 update_time
	private Date updateTime;

	// 对应字段 update_db_version
	private Integer updateDbVersion;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取idx属性值，对应于idx字段
	 * @return idx值
	 */
	public Integer getIdx() {
		return idx;
	}

	/**
	 * 获取name属性值，对应于name字段
	 * @return name值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取description属性值，对应于description字段
	 * @return description值
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取version属性值，对应于version字段
	 * @return version值
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 获取dbId属性值，对应于db_id字段
	 * @return dbId值
	 */
	public Integer getDbId() {
		return dbId;
	}

	/**
	 * 获取lastColumnIdx属性值，对应于last_column_idx字段
	 * @return lastColumnIdx值
	 */
	public Integer getLastColumnIdx() {
		return lastColumnIdx;
	}

	/**
	 * 获取lastIndexIdx属性值，对应于last_index_idx字段
	 * @return lastIndexIdx值
	 */
	public Integer getLastIndexIdx() {
		return lastIndexIdx;
	}

	/**
	 * 获取createTime属性值，对应于create_time字段
	 * @return createTime值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取createDbVersion属性值，对应于create_db_version字段
	 * @return createDbVersion值
	 */
	public Integer getCreateDbVersion() {
		return createDbVersion;
	}

	/**
	 * 获取updateTime属性值，对应于update_time字段
	 * @return updateTime值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 获取updateDbVersion属性值，对应于update_db_version字段
	 * @return updateDbVersion值
	 */
	public Integer getUpdateDbVersion() {
		return updateDbVersion;
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
	 * 设置idx属性值，对应于idx字段
	 * @param idx
	 *            要设置的idx属性值
	 */
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	/**
	 * 设置name属性值，对应于name字段
	 * @param name
	 *            要设置的name属性值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置description属性值，对应于description字段
	 * @param description
	 *            要设置的description属性值
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 设置version属性值，对应于version字段
	 * @param version
	 *            要设置的version属性值
	 */
	public void setVersion(Integer version) {
		this.version = version;
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
	 * 设置lastColumnIdx属性值，对应于last_column_idx字段
	 * @param lastColumnIdx
	 *            要设置的lastColumnIdx属性值
	 */
	public void setLastColumnIdx(Integer lastColumnIdx) {
		this.lastColumnIdx = lastColumnIdx;
	}

	/**
	 * 设置lastIndexIdx属性值，对应于last_index_idx字段
	 * @param lastIndexIdx
	 *            要设置的lastIndexIdx属性值
	 */
	public void setLastIndexIdx(Integer lastIndexIdx) {
		this.lastIndexIdx = lastIndexIdx;
	}

	/**
	 * 设置createTime属性值，对应于create_time字段
	 * @param createTime
	 *            要设置的createTime属性值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置createDbVersion属性值，对应于create_db_version字段
	 * @param createDbVersion
	 *            要设置的createDbVersion属性值
	 */
	public void setCreateDbVersion(Integer createDbVersion) {
		this.createDbVersion = createDbVersion;
	}

	/**
	 * 设置updateTime属性值，对应于update_time字段
	 * @param updateTime
	 *            要设置的updateTime属性值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 设置updateDbVersion属性值，对应于update_db_version字段
	 * @param updateDbVersion
	 *            要设置的updateDbVersion属性值
	 */
	public void setUpdateDbVersion(Integer updateDbVersion) {
		this.updateDbVersion = updateDbVersion;
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
