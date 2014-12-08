/**
 * _Entity.java -- 2014-11-09
 * 作者：郝金隆(www.haojinlong.net) -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.entity;

import java.io.Serializable;

/**
 * OR映射的基础类，提供了必要的属性，并实现了Serializable接口
 * @author 郝金隆【自动生成】
 * @since：2014-11-09
 */
public class _Entity implements Serializable {
	private static final long serialVersionUID = -2952402858868480200L;
	// 开始行
	private Integer _startLine;
	// 结束行
	private Integer _endLine;
	// 每页条
	private Integer _linesPerPage;
	// 第几页（从1开始）
	private Integer _page;
	// orderBy语句
	private String _orderBy;

	/**
	 * @return OrderBy 语句
	 */
	public String get_orderBy() {
		return _orderBy;
	}

	/**
	 * 设置OrderBy语句
	 * 
	 * @param _orderBy
	 *            OrderBy语句，可变参数
	 */
	public void set_orderBy(String ..._orderBy) {
		StringBuffer sb = new StringBuffer();
		for (String str : _orderBy) {
			sb.append(str + ",");
		}
		if (sb.length() > 0) {
			this._orderBy = sb.substring(0, sb.length() - 1);
		}
	}

	/**
	 * @return 开始行，为（页码-1）*每页条数
	 */
	public Integer get_startLine() {
		return _startLine;
	}

	/**
	 * @return 结束行，为 页码*每页条数
	 */
	public Integer get_endLine() {
		return _endLine;
	}

	/**
	 * 
	 * @return 每页条数
	 */
	public Integer get_linesPerPage() {
		return _linesPerPage;
	}

	/**
	 * 
	 * @param _linesPerPage
	 *            每页条数
	 */
	public void set_linesPerPage(Integer _linesPerPage) {
		this._linesPerPage = _linesPerPage;
		if (this._page != null) {
			this._endLine = _page * _linesPerPage;
			this._startLine = (_page - 1) * _linesPerPage;
		}
	}

	/**
	 * 设置页码，从1开始
	 * 
	 * @param _page
	 *            页码
	 */
	public void set_page(Integer _page) {
		this._page = _page;
		if (this._linesPerPage != null) {
			this._endLine = _page * _linesPerPage;
			this._startLine = (_page - 1) * _linesPerPage;
		}
	}

}
