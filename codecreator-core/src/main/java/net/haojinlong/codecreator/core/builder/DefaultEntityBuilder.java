/**
 * # DefaultEntityBuilder.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.builder;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.commons.entity.ColumnInfo;
import net.haojinlong.codecreator.commons.entity.FieldInfo;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.core.resource.EntityTemplateReader;
import net.haojinlong.codecreator.core.resource.PropertiesReader;
import net.haojinlong.tools.date.DateUtils;
import net.haojinlong.tools.json.JsonUtils;
import net.haojinlong.tools.string.StringUtils;
import net.haojinlong.tools.template.LineBasedTemplate;
import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class DefaultEntityBuilder implements EntityBuilder {
	static Logger logger = LoggerFactory.getLogger(DefaultEntityBuilder.class);

	private static String EOL_STRING = "\n";

	// 实体类模板读取器
	private EntityTemplateReader entityTemplateReader;
	// properties文件读取器
	private PropertiesReader propertiesReader;

	/**
	 * @param dbType
	 *            数据库类型
	 * @param daoType
	 *            DAO类型
	 */
	public DefaultEntityBuilder(DbType dbType, DaoType daoType) {
		this.entityTemplateReader = new EntityTemplateReader(dbType, daoType);
		this.propertiesReader = new PropertiesReader(dbType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.EntityBuilder#buildEntity(java
	 * .lang.String, net.haojinlong.codecreator.commons.entity.TableInfo)
	 */
	public String buildEntity(String packageName, TableInfo tableInfo) {
		Template entityTemplate = entityTemplateReader.getEntityTemplate();
		entityTemplate.replace(buildMap(packageName, tableInfo));
		return entityTemplate.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.EntityBuilder#buildEntity(java
	 * .lang.String, net.haojinlong.codecreator.commons.entity.TableInfo,
	 * java.lang.String)
	 */
	public String buildEntity(String packageName, TableInfo tableInfo,
			String source) {
		Map<String, String> map = buildMap(packageName, tableInfo);
		Template entityReplaceTemplate = entityTemplateReader
				.getEntityReplaceTemplate();
		entityReplaceTemplate.replace(map);
		StringBuffer sb = new StringBuffer();
		// 替换中间内容
		try {
			LineBasedTemplate lineBasedTemplate = new LineBasedTemplate(
					new StringReader(source), "autocreate_start",
					"autocreate_end");
			sb.append(lineBasedTemplate.replace(entityReplaceTemplate
					.toString()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 重新修改import代码
		String importStr = buildImport(tableInfo);
		String[] importStrs = importStr.split(EOL_STRING);
		int pos = sb.indexOf("import");
		String strBegin = sb.substring(0, pos);
		String strEnd = sb.substring(pos);
		StringBuffer sb2 = new StringBuffer();
		for (String str : importStrs) {
			if (!sb.toString().contains(str)) {
				sb2.append(str + EOL_STRING);
			}
		}
		return strBegin + sb2.toString() + strEnd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.EntityBuilder#buildBasicEntity
	 * (java.lang.String)
	 */
	public String buildBasicEntity(String packageName) {
		Template basicEntityTemplate = entityTemplateReader
				.getEntityBasicTemplate();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageName);
		map.put("date", DateUtils.getCurrentDay());
		basicEntityTemplate.replace(map);
		return basicEntityTemplate.toString();
	}

	/**
	 * 根据表信息生产要替换的内容
	 * 
	 * @param tableInfo
	 *            表信息
	 * @param packageName
	 *            包名
	 * @return 要替换的map
	 */
	private Map<String, String> buildMap(String packageName, TableInfo tableInfo) {
		buildTableInfo(tableInfo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageName);
		map.put("tableName", tableInfo.getTableName());
		map.put("fileName",
				NameBuilder.buildEntityFileName(tableInfo.getTableName()));
		map.put("date", DateUtils.getCurrentDay());
		map.put("long", new Long(new Random().nextLong()).toString());
		map.put("entityName", tableInfo.getEntityName());
		map.put("import", buildImport(tableInfo));
		map.put("staticField", buildStaticField(tableInfo));
		map.put("fieldDeclare", buildField(tableInfo));
		map.put("getters", buildGetter(tableInfo));
		map.put("setters", buildSetter(tableInfo));
		return map;
	}

	/**
	 * 初始化tableInfo信息
	 * 
	 * @param tableInfo
	 *            要初始化的表信息
	 */
	private void buildTableInfo(TableInfo tableInfo) {
		// 设置实体类名
		tableInfo.setEntityName(NameBuilder.buildEntityName(tableInfo
				.getTableName()));

		// 设置每一列对应的实体类属性信息
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			// 设置对应的属性名
			columnInfo.setFieldName(NameBuilder.buildFieldName(columnInfo
					.getColumnName()));
			// 设置属性信息
			String json = propertiesReader.getFieldInfo(columnInfo
					.getColumnType());
			if (json != null) { // 在配置文件的范围内
				columnInfo.setFieldInfo(JsonUtils.fromJson(json,
						FieldInfo.class));
			} else { // 根据默认的类型进行设置

			}
		}

	}

	/**
	 * 生产要实体类中的getter方法代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return getter代码
	 */
	private String buildGetter(TableInfo tableInfo) {
		Template getterTemplate = entityTemplateReader
				.getEntityGetterTemplate();
		Template getterBoolTemplate = entityTemplateReader
				.getEntityGetterBoolTemplate();
		Template getterSqlDateTemplate = entityTemplateReader
				.getEntityGetterSqlDateTemplate();
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("columnName", columnInfo.getColumnName());
			map.put("fieldName", columnInfo.getFieldName());
			map.put("shortJavaType", columnInfo.getFieldInfo().getShortType());
			map.put("fieldNameUpper",
					StringUtils.firstUpper(columnInfo.getFieldName()));
			if (columnInfo.getFieldInfo().isBoolean()) {
				getterBoolTemplate.replace(map);
				sb.append(getterBoolTemplate.toString());
			} else {
				getterTemplate.replace(map);
				sb.append(getterTemplate.toString());
			}
			if (columnInfo.getFieldInfo().isSqlDate()) {
				getterSqlDateTemplate.replace(map);
				sb.append(getterSqlDateTemplate.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 生产要实体类中的setter方法代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return setter代码
	 */
	private String buildSetter(TableInfo tableInfo) {
		Template setterTemplate = entityTemplateReader
				.getEntitySetterTemplate();
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("columnName", columnInfo.getColumnName());
			map.put("fieldName", columnInfo.getFieldName());
			map.put("shortJavaType", columnInfo.getFieldInfo().getShortType());
			map.put("fieldNameUpper",
					StringUtils.firstUpper(columnInfo.getFieldName()));
			setterTemplate.replace(map);
			sb.append(setterTemplate.toString());
		}
		return sb.toString();
	}

	/**
	 * 生产要实体类中的属性声明代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return 属性声明代码
	 */
	private String buildField(TableInfo tableInfo) {
		Template fieldTemplate = entityTemplateReader.getEntityFieldTemplate();
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("columnName", columnInfo.getColumnName());
			map.put("fieldName", columnInfo.getFieldName());
			map.put("shortJavaType", columnInfo.getFieldInfo().getShortType());
			map.put("fieldNameUpper",
					StringUtils.firstUpper(columnInfo.getFieldName()));
			fieldTemplate.replace(map);
			sb.append(fieldTemplate.toString());
		}
		return sb.toString();
	}

	/**
	 * 生产要实体类中的静态属性声明代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return 静态属性声明代码
	 */
	private String buildStaticField(TableInfo tableInfo) {
		Template staticFieldTemplate = entityTemplateReader
				.getEntityStaticFieldTemplate();
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("columnName", columnInfo.getColumnName());
			map.put("fieldName", columnInfo.getFieldName());
			map.put("shortJavaType", columnInfo.getFieldInfo().getShortType());
			map.put("columnNameUpper", columnInfo.getColumnName().toUpperCase());
			staticFieldTemplate.replace(map);
			sb.append(staticFieldTemplate.toString());
		}
		return sb.toString();
	}

	/**
	 * 生产要实体类中的import代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return import代码
	 */
	private String buildImport(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		List<String> list = new ArrayList<String>();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (columnInfo.getFieldInfo().isNeedImport()) {
				String importStr = "import "
						+ columnInfo.getFieldInfo().getJavaType() + ";"
						+ EOL_STRING;
				if (!list.contains(importStr)) {
					list.add(importStr);
					sb.append(importStr);
				}
			}
		}
		return sb.toString();
	}

}
