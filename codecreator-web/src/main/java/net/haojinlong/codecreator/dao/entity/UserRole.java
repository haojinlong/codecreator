/**
 * #UserRole.java -- 2014-11-30
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_user_role表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-30
 */
public class UserRole extends _Entity {
	private static final long serialVersionUID = 5479762725021898220L;
	public static String TABLE_NAME = "t_user_role";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_USER_ID = "user_id";
	public static String FIELD_USER_ID_ASC = "user_id asc";
	public static String FIELD_USER_ID_DESC = "user_id desc";
	public static String FIELD_ROLE_ID = "role_id";
	public static String FIELD_ROLE_ID_ASC = "role_id asc";
	public static String FIELD_ROLE_ID_DESC = "role_id desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 user_id
	private Integer userId;

	// 对应字段 role_id
	private Integer roleId;



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
	 * 获取roleId属性值，对应于role_id字段
	 * @return roleId值
	 */
	public Integer getRoleId() {
		return roleId;
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
	 * 设置roleId属性值，对应于role_id字段
	 * @param roleId
	 *            要设置的roleId属性值
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
