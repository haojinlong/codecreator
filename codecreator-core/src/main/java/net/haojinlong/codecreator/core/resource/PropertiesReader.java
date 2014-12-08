/**
 * # PropertiesReader.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.core.resource;

import java.io.IOException;
import java.util.Properties;

import net.haojinlong.codecreator.commons.DbType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年10月31日
 *
 */
public class PropertiesReader {
	static Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
	// properties文件地址
	private static String PROPS_PATH = "/net/haojinlong/codecreator/resources/props/";
	// column_to_field文件名（不含前缀）
	private static String COLUMN_TO_FIELD_PROPS = "column_to_field.properties";
	// 字段类型信息到映射类属性信息的映射类
	private Properties columnToFieldProperties = new Properties();

	/**
	 * 构造函数，初始化template和properties配置文件
	 * 
	 * @param dbType
	 *            数据库类型
	 */
	public PropertiesReader(DbType dbType) {
		String prefix = getPrefix(dbType);
		loadProperites(prefix);
	}

	/**
	 * 根据数据库类型获取相应文件的前缀
	 * 
	 * @param dbType
	 *            数据库类型
	 * @return 相应文件的前缀
	 */
	private String getPrefix(DbType dbType) {
		switch (dbType) {
		case mysql:
			return "mysql_";
		default:
			return "mysql_";
		}
	}

	/**
	 * 加载properties文件
	 * @param prefix properties文件的前缀
	 */
	private void loadProperites(String prefix) {
		try {
			columnToFieldProperties.load(this.getClass().getResourceAsStream(
					PROPS_PATH + prefix + COLUMN_TO_FIELD_PROPS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据字段类型信息获取映射类属性信息
	 * 
	 * @param columnType
	 *            字段类型信息
	 * @return 映射类属性信息
	 */
	public String getFieldInfo(String columnType) {
		return columnToFieldProperties.getProperty(columnType);
	}

}
