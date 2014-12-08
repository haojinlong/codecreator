/**
 * #ColumnInfo.java -- 2014-11-09
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_column_info表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class ColumnInfo extends _Entity {
	private static final long serialVersionUID = 88580995023510998L;
	public static String TABLE_NAME = "t_column_info";
	
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
	public static String FIELD_TYPE_CODE = "type_code";
	public static String FIELD_TYPE_CODE_ASC = "type_code asc";
	public static String FIELD_TYPE_CODE_DESC = "type_code desc";
	public static String FIELD_SIZE = "size";
	public static String FIELD_SIZE_ASC = "size asc";
	public static String FIELD_SIZE_DESC = "size desc";
	public static String FIELD_TABLE_ID = "table_id";
	public static String FIELD_TABLE_ID_ASC = "table_id asc";
	public static String FIELD_TABLE_ID_DESC = "table_id desc";
	public static String FIELD_DESCRIPTION = "description";
	public static String FIELD_DESCRIPTION_ASC = "description asc";
	public static String FIELD_DESCRIPTION_DESC = "description desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 idx
	private Integer idx;

	// 对应字段 name
	private String name;

	// 对应字段 type_code
	private Integer typeCode;

	// 对应字段 size
	private Integer size;

	// 对应字段 table_id
	private Integer tableId;

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
	 * 获取typeCode属性值，对应于type_code字段
	 * @return typeCode值
	 */
	public Integer getTypeCode() {
		return typeCode;
	}

	/**
	 * 获取size属性值，对应于size字段
	 * @return size值
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * 获取tableId属性值，对应于table_id字段
	 * @return tableId值
	 */
	public Integer getTableId() {
		return tableId;
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
	 * 设置typeCode属性值，对应于type_code字段
	 * @param typeCode
	 *            要设置的typeCode属性值
	 */
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 设置size属性值，对应于size字段
	 * @param size
	 *            要设置的size属性值
	 */
	public void setSize(Integer size) {
		this.size = size;
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
