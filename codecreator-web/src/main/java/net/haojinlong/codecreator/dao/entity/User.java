/**
 * #User.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.Date;


/**
 * t_user表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class User extends _Entity {
	private static final long serialVersionUID = 9147771481193761272L;
	public static String TABLE_NAME = "t_user";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_PASSWORD = "password";
	public static String FIELD_PASSWORD_ASC = "password asc";
	public static String FIELD_PASSWORD_DESC = "password desc";
	public static String FIELD_FULL_NAME = "full_name";
	public static String FIELD_FULL_NAME_ASC = "full_name asc";
	public static String FIELD_FULL_NAME_DESC = "full_name desc";
	public static String FIELD_PHONE_NO = "phone_no";
	public static String FIELD_PHONE_NO_ASC = "phone_no asc";
	public static String FIELD_PHONE_NO_DESC = "phone_no desc";
	public static String FIELD_EMAIL = "email";
	public static String FIELD_EMAIL_ASC = "email asc";
	public static String FIELD_EMAIL_DESC = "email desc";
	public static String FIELD_CREATE_TIME = "create_time";
	public static String FIELD_CREATE_TIME_ASC = "create_time asc";
	public static String FIELD_CREATE_TIME_DESC = "create_time desc";
	public static String FIELD_IS_AVAILABLE = "is_available";
	public static String FIELD_IS_AVAILABLE_ASC = "is_available asc";
	public static String FIELD_IS_AVAILABLE_DESC = "is_available desc";
	public static String FIELD_EXPIRE_DAY = "expire_day";
	public static String FIELD_EXPIRE_DAY_ASC = "expire_day asc";
	public static String FIELD_EXPIRE_DAY_DESC = "expire_day desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 name
	private String name;

	// 对应字段 password
	private String password;

	// 对应字段 full_name
	private String fullName;

	// 对应字段 phone_no
	private String phoneNo;

	// 对应字段 email
	private String email;

	// 对应字段 create_time
	private Date createTime;

	// 对应字段 is_available
	private Boolean isAvailable;

	// 对应字段 expire_day
	private Date expireDay;



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
	 * 获取password属性值，对应于password字段
	 * @return password值
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获取fullName属性值，对应于full_name字段
	 * @return fullName值
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 获取phoneNo属性值，对应于phone_no字段
	 * @return phoneNo值
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 获取email属性值，对应于email字段
	 * @return email值
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 获取createTime属性值，对应于create_time字段
	 * @return createTime值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取isAvailable属性值，对应于is_available字段
	 * @return isAvailable值
	 */
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	
	
	/**
	 * 获取isAvailable属性值，对应于is_available字段
	 * @return isAvailable值
	 */
	public boolean is_IsAvailable() {
		return isAvailable;
	}

	/**
	 * 获取expireDay属性值，对应于expire_day字段
	 * @return expireDay值
	 */
	public Date getExpireDay() {
		return expireDay;
	}

	/**
	 * 获取expireDay对应的java.sql.Date对象，对应于expire_day字段
	 * @return expireDay对应的java.sql.Date对象
	 */
	public java.sql.Date get_expireDay() {
		return new java.sql.Date(expireDay.getTime());
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
	 * 设置password属性值，对应于password字段
	 * @param password
	 *            要设置的password属性值
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置fullName属性值，对应于full_name字段
	 * @param fullName
	 *            要设置的fullName属性值
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 设置phoneNo属性值，对应于phone_no字段
	 * @param phoneNo
	 *            要设置的phoneNo属性值
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * 设置email属性值，对应于email字段
	 * @param email
	 *            要设置的email属性值
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * 设置isAvailable属性值，对应于is_available字段
	 * @param isAvailable
	 *            要设置的isAvailable属性值
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * 设置expireDay属性值，对应于expire_day字段
	 * @param expireDay
	 *            要设置的expireDay属性值
	 */
	public void setExpireDay(Date expireDay) {
		this.expireDay = expireDay;
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
