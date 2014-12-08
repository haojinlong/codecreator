/**
 * # TemplateReader.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.resource;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class EntityTemplateReader {
	static Logger logger = LoggerFactory.getLogger(EntityTemplateReader.class);
	/**
	 * Template文件地址
	 */
	private static String TEMPLATE_PATH = "/net/haojinlong/codecreator/resources/template/entity/";

	private Template entityBasicTemplate;

	private Template entityTemplate;

	private Template entityReplaceTemplate;

	private Template entityStaticFieldTemplate;

	private Template entityFieldTemplate;

	private Template entitySetterTemplate;

	private Template entityGetterTemplate;

	private Template entityGetterBoolTemplate;

	private Template entityGetterSqlDateTemplate;

	/**
	 * 根据数据库类型和DAO映射类型初始化template信息
	 * 
	 * @param dbType
	 *            数据库类型
	 * @param daoType
	 *            数据持久层类型
	 */
	public EntityTemplateReader(DbType dbType, DaoType daoType) {
		String prefix = getDaoTypePrefix(daoType) + getDbTypePrefxi(dbType);
		loadTemplate(prefix);
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
	 * 根据计划生成的DAO类型生成相应的模板文件前缀
	 * 
	 * @param daoType
	 *            数据库持久层类型
	 * @return 模板文件前缀
	 */
	private String getDaoTypePrefix(DaoType daoType) {
		switch (daoType) {
		case mybatis:
			return "mybatis/";
		case hibernate:
			return "hiberante/";
		case jpa:
			return "jpa/";
		default:
			return "mybatis/";
		}
	}

	/**
	 * template文件初始化
	 * 
	 * @param prefix
	 *            文件前缀
	 */
	private void loadTemplate(String prefix) {
		entityBasicTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "entity_basic.template"));
		entityTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "entity.template"));
		entityReplaceTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "entity_replace.template"));
		entityStaticFieldTemplate = new Template(
				this.getClass()
						.getResourceAsStream(
								TEMPLATE_PATH + prefix
										+ "entity_static_field.template"));
		entityFieldTemplate = new Template(this.getClass().getResourceAsStream(
				TEMPLATE_PATH + prefix + "entity_field.template"));
		entitySetterTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "entity_setter.template"));
		entityGetterTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "entity_getter.template"));
		entityGetterBoolTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "entity_getter_bool.template"));
		entityGetterSqlDateTemplate = new Template(this.getClass()
				.getResourceAsStream(
						TEMPLATE_PATH + prefix + "entity_getter_sqldate.template"));
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
	 * @return the entityBasicTemplate
	 */
	public Template getEntityBasicTemplate() {
		return entityBasicTemplate;
	}

	/**
	 * @return the entityTemplate
	 */
	public Template getEntityTemplate() {
		return entityTemplate;
	}

	/**
	 * @return the entityReplaceTemplate
	 */
	public Template getEntityReplaceTemplate() {
		return entityReplaceTemplate;
	}

	/**
	 * @return the entityStaticFieldTemplate
	 */
	public Template getEntityStaticFieldTemplate() {
		return entityStaticFieldTemplate;
	}

	/**
	 * @return the entityFieldTemplate
	 */
	public Template getEntityFieldTemplate() {
		return entityFieldTemplate;
	}

	/**
	 * @return the entitySetterTemplate
	 */
	public Template getEntitySetterTemplate() {
		return entitySetterTemplate;
	}

	/**
	 * @return the entityGetterTemplate
	 */
	public Template getEntityGetterTemplate() {
		return entityGetterTemplate;
	}

	/**
	 * @return the entityGetterBoolTemplate
	 */
	public Template getEntityGetterBoolTemplate() {
		return entityGetterBoolTemplate;
	}

	/**
	 * @return the entityGetterSqlDateTemplate
	 */
	public Template getEntityGetterSqlDateTemplate() {
		return entityGetterSqlDateTemplate;
	}

}
