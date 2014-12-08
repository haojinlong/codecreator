/**
 * #Role.java -- 2014-11-13
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_role表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-13
 */
public class Role extends _Entity {
	private static final long serialVersionUID = -8344176979272700976L;
	public static String TABLE_NAME = "t_role";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_DESCRIPTION = "description";
	public static String FIELD_DESCRIPTION_ASC = "description asc";
	public static String FIELD_DESCRIPTION_DESC = "description desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 name
	private String name;

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
