/**
 * # NameBuilder.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.commons.builder;

import net.haojinlong.tools.string.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年10月31日
 *
 */
public class NameBuilder {
	static Logger logger = LoggerFactory.getLogger(NameBuilder.class);

	/**
	 * 生成基础Dao接口文件名
	 * 
	 * @return 基础Dao接口文件名
	 */
	public static String buildBasicDaoFileName() {
		return "_Dao.java";
	}

	/**
	 * 生成基础实体类文件名
	 * 
	 * @return 基础实体类文件名
	 */
	public static String buildBasicEntityFileName() {
		return "_Entity.java";
	}

	/**
	 * 根据表名生成映射实体类名
	 * 
	 * @param tableName
	 *            表名
	 * @return 映射类的属性名
	 */
	public static String buildEntityName(String tableName) {
		String[] strs = null;
		if (tableName.toUpperCase().startsWith("T_") && tableName.length() > 2) {
			strs = tableName.substring(2).split("_");
		} else {
			strs = tableName.split("_");
		}
		StringBuffer sb = new StringBuffer();
		for (String str : strs) {
			sb.append(StringUtils.firstUpper(str.toLowerCase()));
		}
		return sb.toString();
	}

	/**
	 * 根据表名生成映射类文件名
	 * 
	 * @param tableName
	 *            表名
	 * @return 对应的映射类文件名
	 */
	public static String buildEntityFileName(String tableName) {
		return buildEntityName(tableName) + ".java";
	}

	/**
	 * 根据表名生成DAO接口类的文件名
	 * 
	 * @param tableName
	 *            表名
	 * @return DAO接口类的文件名
	 */
	public static String buildDaoFileName(String tableName) {
		return buildEntityName(tableName) + "Dao.java";
	}

	/**
	 * 根据表名生成mybatis DAO Mapper配置文件的文件名
	 * 
	 * @param tableName
	 *            表名
	 * @return DAO Mapper配置文件的文件名
	 */
	public static String buildDaoMapperFileName(String tableName) {
		return buildEntityName(tableName) + "Dao.xml";
	}

	/**
	 * 根据表名生成DAO实现类类名（针对hibernate或jpa）
	 * 
	 * @param tableName
	 *            表名
	 * @return DAO实现类类名
	 */
	public static String buildDaoImplFileName(String tableName) {
		return buildDaoFileName(tableName) + "DaoImpl.java";
	}

	/**
	 * 根据列名称生成属性名
	 * 
	 * @param columnName
	 *            字段名
	 * @return 对应的映射类中的属性名
	 */
	public static String buildFieldName(String columnName) {
		String[] strs = columnName.split("_");
		StringBuffer sb = new StringBuffer();
		for (String str : strs) {
			sb.append(StringUtils.firstUpper(str.toLowerCase()));
		}
		return StringUtils.firstLower(sb.toString());
	}
}
