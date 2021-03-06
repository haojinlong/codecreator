/**
 * #SysColumnType.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * sys_column_type表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class SysColumnType extends _Entity {
	private static final long serialVersionUID = -5692959426993309319L;
	public static String TABLE_NAME = "sys_column_type";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_CODE = "code";
	public static String FIELD_CODE_ASC = "code asc";
	public static String FIELD_CODE_DESC = "code desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_NEED_LENGTH = "need_length";
	public static String FIELD_NEED_LENGTH_ASC = "need_length asc";
	public static String FIELD_NEED_LENGTH_DESC = "need_length desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 code
	private Integer code;

	// 对应字段 name
	private String name;

	// 对应字段 need_length
	private Boolean needLength;



	/**
	 * 获取id属性值，对应于id字段
	 * @return id值
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 获取code属性值，对应于code字段
	 * @return code值
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 获取name属性值，对应于name字段
	 * @return name值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取needLength属性值，对应于need_length字段
	 * @return needLength值
	 */
	public Boolean getNeedLength() {
		return needLength;
	}
	
	
	/**
	 * 获取needLength属性值，对应于need_length字段
	 * @return needLength值
	 */
	public boolean is_NeedLength() {
		return needLength;
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
	 * 设置code属性值，对应于code字段
	 * @param code
	 *            要设置的code属性值
	 */
	public void setCode(Integer code) {
		this.code = code;
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
	 * 设置needLength属性值，对应于need_length字段
	 * @param needLength
	 *            要设置的needLength属性值
	 */
	public void setNeedLength(Boolean needLength) {
		this.needLength = needLength;
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
