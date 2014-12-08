/**
 * # DaoTemplateReader.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.resource;

import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class DaoTemplateReader {
	static Logger logger = LoggerFactory.getLogger(DaoTemplateReader.class);
	/**
	 * Template文件地址
	 */
	private static String TEMPLATE_PATH = "/net/haojinlong/codecreator/resources/template/dao/";

	private Template daoTemplate;

	private Template daoBasicTemplate;

	/**
	 * 初始化，加载相应的模板文件
	 */
	public DaoTemplateReader() {
		loadTemplate();
	}

	private void loadTemplate() {
		daoBasicTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + "dao_basic.template"));
		daoTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + "dao.template"));
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * @return the tEMPLATE_PATH
	 */
	public static String getTEMPLATE_PATH() {
		return TEMPLATE_PATH;
	}

	/**
	 * @return the daoTemplate
	 */
	public Template getDaoTemplate() {
		return daoTemplate;
	}

	/**
	 * @return the daoBasicTemplate
	 */
	public Template getDaoBasicTemplate() {
		return daoBasicTemplate;
	}

}
