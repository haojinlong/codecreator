/**
 * # EntityBuilder.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.builder;

import net.haojinlong.codecreator.commons.entity.TableInfo;

/**
 * 实体类生成器
 * 
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public interface EntityBuilder {
	/**
	 * 根据表信息，生成实体类内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return 实体类内容
	 */
	public String buildEntity(String packageName, TableInfo tableInfo);

	/**
	 * 根据表信息和可能替换的数据信息，生产实体类内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表名
	 * @param source
	 *            原已修改的实体类内容
	 * @return 实体类内容
	 */
	public String buildEntity(String packageName, TableInfo tableInfo,
			String source);

	/**
	 * 生产基础实体类内容
	 * 
	 * @param packageName
	 *            包名
	 * @return 基础实体类内容
	 */
	public String buildBasicEntity(String packageName);

}
