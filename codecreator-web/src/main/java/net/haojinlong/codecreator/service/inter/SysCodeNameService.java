/**
 * # SysCodeNameService.java -- (2014年12月7日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.inter;

import java.util.List;
import java.util.Map;

import net.haojinlong.commons.entity.CodeText;

/**
 * @author 郝金隆
 * @since 2014年12月7日
 */
public interface SysCodeNameService {

	/**
	 * 根据数据库和列表名称获取翻译列表
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public List<CodeText> list(String tableName, String columnName);

	/**
	 * 根据数据库和列表名，获取要翻译的codeMap值
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public Map<Integer, String> getMap(String tableName, String columnName);

}
