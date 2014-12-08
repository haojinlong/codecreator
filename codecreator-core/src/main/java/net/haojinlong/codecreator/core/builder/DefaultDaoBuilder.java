/**
 * # DefaultDaoBuilder.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.builder;

import java.util.HashMap;
import java.util.Map;

import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.core.resource.DaoTemplateReader;
import net.haojinlong.tools.date.DateUtils;
import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class DefaultDaoBuilder implements DaoBuilder {
	static Logger logger = LoggerFactory.getLogger(DefaultDaoBuilder.class);

	DaoTemplateReader daoTemplateReader = new DaoTemplateReader();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.DaoBuilder#buildBasicDao(java
	 * .lang.String)
	 */
	public String buildBasicDao(String packageName) {
		Template basicDaoTemplate = daoTemplateReader.getDaoBasicTemplate();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageName);
		map.put("date", DateUtils.getCurrentDay());
		basicDaoTemplate.replace(map);
		return basicDaoTemplate.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.DaoBuilder#buildDao(java.lang
	 * .String, net.haojinlong.codecreator.commons.entity.TableInfo)
	 */
	public String buildDao(String packageName, TableInfo tableInfo) {
		Template daoTemplate = daoTemplateReader.getDaoTemplate();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageName);
		map.put("entityName",
				NameBuilder.buildEntityName(tableInfo.getTableName()));
		map.put("date", DateUtils.getCurrentDay());
		daoTemplate.replace(map);
		return daoTemplate.toString();
	}

}
