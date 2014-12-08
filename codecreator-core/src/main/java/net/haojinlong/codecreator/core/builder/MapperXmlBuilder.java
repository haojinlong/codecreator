/**
 * # MapperBuilder.java -- (2014年11月1日)
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
public interface MapperXmlBuilder {

	/**
	 * 生产Mapper的xml文件
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return Mapper XML文件
	 */
	public String buildMapper(String packageName, TableInfo tableInfo);

	/**
	 * 生产Mapper XML文件
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @param source
	 *            已经修改后的文件
	 * @return Mapper XML文件
	 */
	public String buildMapper(String packageName, TableInfo tableInfo,
			String source);

}
