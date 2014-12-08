/**
 * #UserDatabase.java -- 2014-11-30
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_user_database表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-30
 */
public class UserDatabase extends _Entity {
	private static final long serialVersionUID = -932438087668630621L;
	public static String TABLE_NAME = "t_user_database";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_USER_ID = "user_id";
	public static String FIELD_USER_ID_ASC = "user_id asc";
	public static String FIELD_USER_ID_DESC = "user_id desc";
	public static String FIELD_DATABASE_ID = "database_id";
	public static String FIELD_DATABASE_ID_ASC = "database_id asc";
	public static String FIELD_DATABASE_ID_DESC = "database_id desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 user_id
	private Integer userId;

	// 对应字段 database_id
	private Integer databaseId;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取userId属性值，对应于user_id字段
	 * @return userId值
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 获取databaseId属性值，对应于database_id字段
	 * @return databaseId值
	 */
	public Integer getDatabaseId() {
		return databaseId;
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
	 * 设置userId属性值，对应于user_id字段
	 * @param userId
	 *            要设置的userId属性值
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 设置databaseId属性值，对应于database_id字段
	 * @param databaseId
	 *            要设置的databaseId属性值
	 */
	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
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
