/**
 * # DefaultMapperXmlBuilder.java -- (2014年11月1日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.builder;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.commons.entity.ColumnInfo;
import net.haojinlong.codecreator.commons.entity.FieldInfo;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.core.resource.MapperTemplateReader;
import net.haojinlong.codecreator.core.resource.PropertiesReader;
import net.haojinlong.tools.date.DateUtils;
import net.haojinlong.tools.json.JsonUtils;
import net.haojinlong.tools.template.LineBasedTemplate;
import net.haojinlong.tools.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月1日
 *
 */
public class DefaultMapperXmlBuilder implements MapperXmlBuilder {
	static Logger logger = LoggerFactory
			.getLogger(DefaultMapperXmlBuilder.class);

	// Mapper XML模板文件读取器
	private MapperTemplateReader mapperTemplateReader;
	// Properties文件读取器
	private PropertiesReader propertiesReader;

	/**
	 * @param dbType
	 *            数据库类型
	 */
	public DefaultMapperXmlBuilder(DbType dbType) {
		mapperTemplateReader = new MapperTemplateReader(dbType);
		propertiesReader = new PropertiesReader(dbType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.MapperXmlBuilder#buildMapper(
	 * java.lang.String, net.haojinlong.codecreator.commons.entity.TableInfo)
	 */
	public String buildMapper(String packageName, TableInfo tableInfo) {
		Template mapperTemplate = mapperTemplateReader.getMapperTemplate();
		mapperTemplate.replace(buildMap(packageName, tableInfo));
		return mapperTemplate.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.core.builder.MapperXmlBuilder#buildMapper(
	 * java.lang.String, net.haojinlong.codecreator.commons.entity.TableInfo,
	 * java.lang.String)
	 */
	public String buildMapper(String packageName, TableInfo tableInfo,
			String source) {
		Template mapperReplaceTemplate = mapperTemplateReader
				.getMapperReplaceTemplate();
		mapperReplaceTemplate.replace(buildMap(packageName, tableInfo));
		StringBuffer sb = new StringBuffer();
		try {
			LineBasedTemplate lineBasedTemplate = new LineBasedTemplate(
					new StringReader(source), "autocreate_start",
					"autocreate_end");
			sb.append(lineBasedTemplate.replace(mapperReplaceTemplate
					.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 创建mapper XML模板要替换的map
	 * 
	 * @param tableInfo
	 *            表信息
	 * @param packageName
	 *            包名
	 * @return 模板要替换的map
	 */
	private Map<String, String> buildMap(String packageName, TableInfo tableInfo) {
		buildTableInfo(tableInfo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageName);
		map.put("tableName", tableInfo.getTableName());
		map.put("entityName", tableInfo.getEntityName());
		map.put("columnAll", buildColumnAll(tableInfo));
		map.put("fieldAll", buildFieldAll(tableInfo));
		map.put("idMap", buildIdMap(tableInfo));
		map.put("resultMap", buildResultMap(tableInfo));
		map.put("bind", buildBind(tableInfo));
		map.put("testLike", buildTestLike(tableInfo));
		map.put("testBy", buildTestBy(tableInfo));
		map.put("set", buildSet(tableInfo));
		map.put("date", DateUtils.getCurrentDay());
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
				columnInfo.initFieldInfo();
			}
		}

	}

	/**
	 * 创建columnAll语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return columnAll语句
	 */
	private String buildColumnAll(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (!columnInfo.getColumnName().toUpperCase().equals("ID")) {
				sb.append(columnInfo.getColumnName() + ",");
			}
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	/**
	 * 创建fieldAll语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return fieldAll语句
	 */
	private String buildFieldAll(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template fieldTemplate = mapperTemplateReader.getMapperFieldTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (!columnInfo.getColumnName().toUpperCase().equals("ID")) {
				fieldTemplate.replace(buildColumnMap(columnInfo));
				sb.append(fieldTemplate.toString() + ",");
			}
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	/**
	 * 创建testLike语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return testLike语句
	 */
	private String buildTestLike(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template testByTemplate = mapperTemplateReader
				.getMapperTestByTemplate();
		Template testLikeTemplate = mapperTemplateReader
				.getMapperTestLikeTemplate();
		Template testBySqlTemplate = mapperTemplateReader
				.getMapperTestBySqlDateTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (columnInfo.getFieldInfo().isSqlDate()) {
				testBySqlTemplate.replace(buildColumnMap(columnInfo));
				sb.append(testBySqlTemplate.toString());
			} else {
				if (columnInfo.getFieldInfo().getShortType().equals("String")) {
					testLikeTemplate.replace(buildColumnMap(columnInfo));
					sb.append(testLikeTemplate.toString());
				} else {
					testByTemplate.replace(buildColumnMap(columnInfo));
					sb.append(testByTemplate.toString());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 生成testBy语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return testBy语句
	 */
	private String buildTestBy(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template testByTemplate = mapperTemplateReader
				.getMapperTestByTemplate();
		Template testBySqlTemplate = mapperTemplateReader
				.getMapperTestBySqlDateTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (columnInfo.getFieldInfo().isSqlDate()) {
				testBySqlTemplate.replace(buildColumnMap(columnInfo));
				sb.append(testBySqlTemplate.toString());
			} else {
				testByTemplate.replace(buildColumnMap(columnInfo));
				sb.append(testByTemplate.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 生成set语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return set语句
	 */
	private String buildSet(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template setTemplate = mapperTemplateReader.getMapperSetTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (!columnInfo.getColumnName().toUpperCase().equals("ID")) {
				setTemplate.replace(buildColumnMap(columnInfo));
				sb.append(setTemplate.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 生成resultMap语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return resultMap语句
	 */
	private String buildResultMap(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template resultMapTemplate = mapperTemplateReader
				.getMapperResultMapTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (!columnInfo.getColumnName().toUpperCase().equals("ID")) {
				resultMapTemplate.replace(buildColumnMap(columnInfo));
				sb.append(resultMapTemplate.toString());
			}
		}

		return sb.toString();
	}

	/**
	 * 创建idMap语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return idMap语句
	 */
	private String buildIdMap(TableInfo tableInfo) {
		Template idMapTemplate = mapperTemplateReader.getMapperIdMapTemplate();
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (columnInfo.getColumnName().toUpperCase().equals("ID")) {
				idMapTemplate.replace(buildColumnMap(columnInfo));
				sb.append(idMapTemplate.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 生成bind语句
	 * 
	 * @param tableInfo
	 *            表信息
	 * @return bind语句
	 */
	private String buildBind(TableInfo tableInfo) {
		StringBuffer sb = new StringBuffer();
		Template bindTemplate = mapperTemplateReader.getMapperBindTemplate();
		for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
			if (columnInfo.getFieldInfo().getShortType().equals("String")) {
				bindTemplate.replace(buildColumnMap(columnInfo));
				sb.append(bindTemplate.toString());
			}
		}

		return sb.toString();
	}

	/**
	 * 根据列信息生成要替换的Map
	 * 
	 * @param columnInfo
	 *            列信息
	 * @return 模板替换map
	 */
	private Map<String, String> buildColumnMap(ColumnInfo columnInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("columnName", columnInfo.getColumnName());
		map.put("fieldName", columnInfo.getFieldName());
		return map;
	}

}
