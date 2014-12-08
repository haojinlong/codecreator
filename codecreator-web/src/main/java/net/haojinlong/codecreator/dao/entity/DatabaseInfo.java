/**
 * #DatabaseInfo.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.Date;


/**
 * t_database_info表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class DatabaseInfo extends _Entity {
	private static final long serialVersionUID = 2653729147946094538L;
	public static String TABLE_NAME = "t_database_info";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_DB_KEY = "db_key";
	public static String FIELD_DB_KEY_ASC = "db_key asc";
	public static String FIELD_DB_KEY_DESC = "db_key desc";
	public static String FIELD_DESCRIPTION = "description";
	public static String FIELD_DESCRIPTION_ASC = "description asc";
	public static String FIELD_DESCRIPTION_DESC = "description desc";
	public static String FIELD_VERSION = "version";
	public static String FIELD_VERSION_ASC = "version asc";
	public static String FIELD_VERSION_DESC = "version desc";
	public static String FIELD_USER_ID = "user_id";
	public static String FIELD_USER_ID_ASC = "user_id asc";
	public static String FIELD_USER_ID_DESC = "user_id desc";
	public static String FIELD_PROJ_ID = "proj_id";
	public static String FIELD_PROJ_ID_ASC = "proj_id asc";
	public static String FIELD_PROJ_ID_DESC = "proj_id desc";
	public static String FIELD_PROJ_PERMISSION = "proj_permission";
	public static String FIELD_PROJ_PERMISSION_ASC = "proj_permission asc";
	public static String FIELD_PROJ_PERMISSION_DESC = "proj_permission desc";
	public static String FIELD_CREATE_TIME = "create_time";
	public static String FIELD_CREATE_TIME_ASC = "create_time asc";
	public static String FIELD_CREATE_TIME_DESC = "create_time desc";
	public static String FIELD_UPDATE_TIME = "update_time";
	public static String FIELD_UPDATE_TIME_ASC = "update_time asc";
	public static String FIELD_UPDATE_TIME_DESC = "update_time desc";
	public static String FIELD_DISABLED = "disabled";
	public static String FIELD_DISABLED_ASC = "disabled asc";
	public static String FIELD_DISABLED_DESC = "disabled desc";
	public static String FIELD_LAST_TABLE_IDX = "last_table_idx";
	public static String FIELD_LAST_TABLE_IDX_ASC = "last_table_idx asc";
	public static String FIELD_LAST_TABLE_IDX_DESC = "last_table_idx desc";
	public static String FIELD_LOCKED = "locked";
	public static String FIELD_LOCKED_ASC = "locked asc";
	public static String FIELD_LOCKED_DESC = "locked desc";
	public static String FIELD_LOCK_USER_ID = "lock_user_id";
	public static String FIELD_LOCK_USER_ID_ASC = "lock_user_id asc";
	public static String FIELD_LOCK_USER_ID_DESC = "lock_user_id desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 name
	private String name;

	// 对应字段 db_key
	private String dbKey;

	// 对应字段 description
	private String description;

	// 对应字段 version
	private Integer version;

	// 对应字段 user_id
	private Integer userId;

	// 对应字段 proj_id
	private Integer projId;

	// 对应字段 proj_permission
	private Integer projPermission;

	// 对应字段 create_time
	private Date createTime;

	// 对应字段 update_time
	private Date updateTime;

	// 对应字段 disabled
	private Boolean disabled;

	// 对应字段 last_table_idx
	private Integer lastTableIdx;

	// 对应字段 locked
	private Boolean locked;

	// 对应字段 lock_user_id
	private Integer lockUserId;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取name属性值，对应于name字段
	 * @return name值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取dbKey属性值，对应于db_key字段
	 * @return dbKey值
	 */
	public String getDbKey() {
		return dbKey;
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
	 * 获取userId属性值，对应于user_id字段
	 * @return userId值
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 获取projId属性值，对应于proj_id字段
	 * @return projId值
	 */
	public Integer getProjId() {
		return projId;
	}

	/**
	 * 获取projPermission属性值，对应于proj_permission字段
	 * @return projPermission值
	 */
	public Integer getProjPermission() {
		return projPermission;
	}

	/**
	 * 获取createTime属性值，对应于create_time字段
	 * @return createTime值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取updateTime属性值，对应于update_time字段
	 * @return updateTime值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 获取disabled属性值，对应于disabled字段
	 * @return disabled值
	 */
	public Boolean getDisabled() {
		return disabled;
	}
	
	
	/**
	 * 获取disabled属性值，对应于disabled字段
	 * @return disabled值
	 */
	public boolean is_Disabled() {
		return disabled;
	}

	/**
	 * 获取lastTableIdx属性值，对应于last_table_idx字段
	 * @return lastTableIdx值
	 */
	public Integer getLastTableIdx() {
		return lastTableIdx;
	}

	/**
	 * 获取locked属性值，对应于locked字段
	 * @return locked值
	 */
	public Boolean getLocked() {
		return locked;
	}
	
	
	/**
	 * 获取locked属性值，对应于locked字段
	 * @return locked值
	 */
	public boolean is_Locked() {
		return locked;
	}

	/**
	 * 获取lockUserId属性值，对应于lock_user_id字段
	 * @return lockUserId值
	 */
	public Integer getLockUserId() {
		return lockUserId;
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
	 * 设置name属性值，对应于name字段
	 * @param name
	 *            要设置的name属性值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置dbKey属性值，对应于db_key字段
	 * @param dbKey
	 *            要设置的dbKey属性值
	 */
	public void setDbKey(String dbKey) {
		this.dbKey = dbKey;
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
	 * 设置userId属性值，对应于user_id字段
	 * @param userId
	 *            要设置的userId属性值
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 设置projId属性值，对应于proj_id字段
	 * @param projId
	 *            要设置的projId属性值
	 */
	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	/**
	 * 设置projPermission属性值，对应于proj_permission字段
	 * @param projPermission
	 *            要设置的projPermission属性值
	 */
	public void setProjPermission(Integer projPermission) {
		this.projPermission = projPermission;
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
	 * 设置updateTime属性值，对应于update_time字段
	 * @param updateTime
	 *            要设置的updateTime属性值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 设置disabled属性值，对应于disabled字段
	 * @param disabled
	 *            要设置的disabled属性值
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * 设置lastTableIdx属性值，对应于last_table_idx字段
	 * @param lastTableIdx
	 *            要设置的lastTableIdx属性值
	 */
	public void setLastTableIdx(Integer lastTableIdx) {
		this.lastTableIdx = lastTableIdx;
	}

	/**
	 * 设置locked属性值，对应于locked字段
	 * @param locked
	 *            要设置的locked属性值
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * 设置lockUserId属性值，对应于lock_user_id字段
	 * @param lockUserId
	 *            要设置的lockUserId属性值
	 */
	public void setLockUserId(Integer lockUserId) {
		this.lockUserId = lockUserId;
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
