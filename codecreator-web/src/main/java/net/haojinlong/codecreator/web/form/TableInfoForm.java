/**
 * # TableInfoForm.java -- (2014年12月5日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.form;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.ColumnInfo;
import net.haojinlong.codecreator.dao.entity.TableInfo;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年12月5日
 */
public class TableInfoForm {
	static Logger logger = LoggerFactory.getLogger(TableInfoForm.class);

	private TableInfo tableInfo;

	private List<ColumnInfo> columnList;

	private List<ColumnInfo> addColumnList;

	/**
	 * @return the tableInfo
	 */
	public TableInfo getTableInfo() {
		return tableInfo;
	}

	/**
	 * @param tableInfo
	 *            the tableInfo to set
	 */
	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	/**
	 * @return the columnList
	 */
	public List<ColumnInfo> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList
	 *            the columnList to set
	 */
	public void setColumnList(List<ColumnInfo> columnList) {
		this.columnList = columnList;
	}

	/**
	 * @return the addColumnList
	 */
	public List<ColumnInfo> getAddColumnList() {
		return addColumnList;
	}

	/**
	 * @param addColumnList
	 *            the addColumnList to set
	 */
	public void setAddColumnList(List<ColumnInfo> addColumnList) {
		this.addColumnList = addColumnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
