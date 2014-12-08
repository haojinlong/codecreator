/**
 * # IndexForm.java -- (2014年12月7日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.form;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.IndexInfo;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年12月7日
 */
public class IndexForm {
	static Logger logger = LoggerFactory.getLogger(IndexForm.class);
	private List<IndexInfo> indexList;
	private List<IndexInfo> addIndexList;

	/**
	 * @return the indexList
	 */
	public List<IndexInfo> getIndexList() {
		return indexList;
	}

	/**
	 * @param indexList
	 *            the indexList to set
	 */
	public void setIndexList(List<IndexInfo> indexList) {
		this.indexList = indexList;
	}

	/**
	 * @return the addIndexList
	 */
	public List<IndexInfo> getAddIndexList() {
		return addIndexList;
	}

	/**
	 * @param addIndexList
	 *            the addIndexList to set
	 */
	public void setAddIndexList(List<IndexInfo> addIndexList) {
		this.addIndexList = addIndexList;
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
