/**
 * #IndexInfo.java -- 2014-12-07
 * 作者：org.haojinlong.codecreator -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * t_index_info表的映射类
 * 
 * @author 郝金隆【自动生成】
 * @since：2014-12-07
 */
public class IndexInfo extends _Entity {
	private static final long serialVersionUID = -883379322057997536L;
	public static String TABLE_NAME = "t_index_info";
	
	//autocreate_start

	public static String FIELD_ID = "id";
	public static String FIELD_ID_ASC = "id asc";
	public static String FIELD_ID_DESC = "id desc";
	public static String FIELD_IDX = "idx";
	public static String FIELD_IDX_ASC = "idx asc";
	public static String FIELD_IDX_DESC = "idx desc";
	public static String FIELD_TABLE_ID = "table_id";
	public static String FIELD_TABLE_ID_ASC = "table_id asc";
	public static String FIELD_TABLE_ID_DESC = "table_id desc";
	public static String FIELD_NAME = "name";
	public static String FIELD_NAME_ASC = "name asc";
	public static String FIELD_NAME_DESC = "name desc";
	public static String FIELD_CONTENT = "content";
	public static String FIELD_CONTENT_ASC = "content asc";
	public static String FIELD_CONTENT_DESC = "content desc";


	// 对应字段 id
	private Integer id;

	// 对应字段 idx
	private Integer idx;

	// 对应字段 table_id
	private Integer tableId;

	// 对应字段 name
	private String name;

	// 对应字段 content
	private String content;



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
	 * 获取tableId属性值，对应于table_id字段
	 * @return tableId值
	 */
	public Integer getTableId() {
		return tableId;
	}

	/**
	 * 获取name属性值，对应于name字段
	 * @return name值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取content属性值，对应于content字段
	 * @return content值
	 */
	public String getContent() {
		return content;
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
	 * 设置tableId属性值，对应于table_id字段
	 * @param tableId
	 *            要设置的tableId属性值
	 */
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
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
	 * 设置content属性值，对应于content字段
	 * @param content
	 *            要设置的content属性值
	 */
	public void setContent(String content) {
		this.content = content;
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
