/**
 * # DaoBuilder.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.builder;

import net.haojinlong.codecreator.commons.entity.TableInfo;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public interface DaoBuilder {
	/**
	 * 生成基础Dao接口内容
	 * 
	 * @param packageName
	 *            包名
	 * @return 基础Dao接口内容
	 */
	public String buildBasicDao(String packageName);

	/**
	 * 根据表信息生成Dao接口内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return Dao接口内容
	 */
	public String buildDao(String packageName, TableInfo tableInfo);

}
