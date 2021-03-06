/**
 * # Dao.java -- (2014-11-09)
 * 作者：郝金隆(www.haojinlong.net) -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.inter;

import java.util.List;

/**
 * @author 郝金隆【自动生成】
 * @since 2014-11-09
 *
 */
public interface _Dao<T> {
	/*
	 * ----------------------------------------------------------------------*
	 * count method
	 * -----------------------------------------------------------------------
	 */
	public int countAll();

	public int countByEntity(T t);

	public int countLikeEntity(T t);

	/*
	 * ----------------------------------------------------------------------*
	 * select method
	 * -----------------------------------------------------------------------
	 */
	public T selectById(Integer Id);

	/*
	 * ----------------------------------------------------------------------*
	 * insert、update method
	 * -----------------------------------------------------------------------
	 */
	public void insert(T t);

	public void update(T t);

	/*
	 * ----------------------------------------------------------------------*
	 * delete method
	 * -----------------------------------------------------------------------
	 */
	public void delete(T t);

	public void deleteById(Integer id);

	public void deleteByEntity(T t);

	public void deleteLikeEntity(T t);

	/*
	 * ----------------------------------------------------------------------*
	 * list method
	 * -----------------------------------------------------------------------
	 */
	public List<T> listAll();

	public List<T> listByEntity(T t);

	public List<T> listLikeEntity(T t);

}
