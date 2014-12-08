/**
 * # MapperTemplateReader.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.resource;

import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class MapperTemplateReader {
	static Logger logger = LoggerFactory.getLogger(MapperTemplateReader.class);

	/**
	 * Template文件地址
	 */
	private static String TEMPLATE_PATH = "/net/haojinlong/codecreator/resources/template/mapper/";

	private Template mapperTemplate;
	private Template mapperReplaceTemplate;
	private Template mapperResultMapTemplate;
	private Template mapperIdMapTemplate;
	private Template mapperBindTemplate;
	private Template mapperSetTemplate;
	private Template mapperTestByTemplate;
	private Template mapperTestBySqlDateTemplate;
	private Template mapperTestLikeTemplate;
	private Template mapperFieldTemplate;

	/**
	 * 根据数据库类型进行初始化
	 * 
	 * @param dbType
	 *            数据库类型
	 */
	public MapperTemplateReader(DbType dbType) {
		String prefix = getDbTypePrefxi(dbType);
		loadTemplate(prefix);
	}

	/**
	 * 根据前缀价值模板
	 * 
	 * @param prefix
	 *            前缀
	 */
	private void loadTemplate(String prefix) {
		mapperTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "mapper.template"));
		mapperReplaceTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "mapper_replace.template"));
		mapperResultMapTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "mapper_result_map.template"));
		mapperIdMapTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "mapper_id_map.template"));
		mapperBindTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "mapper_bind.template"));
		mapperSetTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "mapper_set.template"));
		mapperTestByTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "mapper_test_by.template"));
		mapperTestBySqlDateTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix
								+ "mapper_test_by_sqldate.template"));
		mapperTestLikeTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "mapper_test_like.template"));
		mapperFieldTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "mapper_field.template"));
	}

	/**
	 * 根据数据库类型获取对应的模板文件前缀
	 * 
	 * @param dbType
	 *            数据库类型
	 * @return 模板文件前缀
	 */
	private String getDbTypePrefxi(DbType dbType) {
		switch (dbType) {
		case mysql:
			return "mysql/";
		case oracle:
			return "oracle/";
		case sybase:
			return "sybase/";
		case sqlserver:
			return "sqlserver/";
		default:
			return "mysql/";
		}
	}

	/**
	 * @return the mapperTemplate
	 */
	public Template getMapperTemplate() {
		return mapperTemplate;
	}

	/**
	 * @return the mapperReplaceTemplate
	 */
	public Template getMapperReplaceTemplate() {
		return mapperReplaceTemplate;
	}

	/**
	 * @return the mapperResultMapTemplate
	 */
	public Template getMapperResultMapTemplate() {
		return mapperResultMapTemplate;
	}

	/**
	 * @return the mapperIdMapTemplate
	 */
	public Template getMapperIdMapTemplate() {
		return mapperIdMapTemplate;
	}

	/**
	 * @return the mapperBindTemplate
	 */
	public Template getMapperBindTemplate() {
		return mapperBindTemplate;
	}

	/**
	 * @return the mapperSetTemplate
	 */
	public Template getMapperSetTemplate() {
		return mapperSetTemplate;
	}

	/**
	 * @return the mapperTestByTemplate
	 */
	public Template getMapperTestByTemplate() {
		return mapperTestByTemplate;
	}

	/**
	 * @return the mapperTestLikeTemplate
	 */
	public Template getMapperTestLikeTemplate() {
		return mapperTestLikeTemplate;
	}

	/**
	 * @return the mapperFieldTemplate
	 */
	public Template getMapperFieldTemplate() {
		return mapperFieldTemplate;
	}

	/**
	 * @return the mapperTestBySqlDateTemplate
	 */
	public Template getMapperTestBySqlDateTemplate() {
		return mapperTestBySqlDateTemplate;
	}

}
