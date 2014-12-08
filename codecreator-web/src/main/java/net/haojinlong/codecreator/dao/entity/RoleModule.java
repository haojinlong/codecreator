/**
 * #RoleModule.java -- 2014-11-13
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_role_module表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-13
 */
public class RoleModule extends _Entity {
	private static final long serialVersionUID = -5123235315758054140L;
	public static String TABLE_NAME = "t_role_module";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_MODULE_CODE = "module_code";
	public static String FIELD_MODULE_CODE_ASC = "module_code asc";
	public static String FIELD_MODULE_CODE_DESC = "module_code desc";
	public static String FIELD_ROLE_ID = "role_id";
	public static String FIELD_ROLE_ID_ASC = "role_id asc";
	public static String FIELD_ROLE_ID_DESC = "role_id desc";
	public static String FIELD_OPER_CODE = "oper_code";
	public static String FIELD_OPER_CODE_ASC = "oper_code asc";
	public static String FIELD_OPER_CODE_DESC = "oper_code desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 module_code
	private String moduleCode;

	// 对应字段 role_id
	private Integer roleId;

	// 对应字段 oper_code
	private String operCode;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取moduleCode属性值，对应于module_code字段
	 * @return moduleCode值
	 */
	public String getModuleCode() {
		return moduleCode;
	}

	/**
	 * 获取roleId属性值，对应于role_id字段
	 * @return roleId值
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 获取operCode属性值，对应于oper_code字段
	 * @return operCode值
	 */
	public String getOperCode() {
		return operCode;
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
	 * 设置moduleCode属性值，对应于module_code字段
	 * @param moduleCode
	 *            要设置的moduleCode属性值
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**
	 * 设置roleId属性值，对应于role_id字段
	 * @param roleId
	 *            要设置的roleId属性值
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 设置operCode属性值，对应于oper_code字段
	 * @param operCode
	 *            要设置的operCode属性值
	 */
	public void setOperCode(String operCode) {
		this.operCode = operCode;
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
