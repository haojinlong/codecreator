/**
 * # SysCodeNameServiceImpl.java -- (2014年12月7日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.haojinlong.codecreator.dao.entity.SysCodeText;
import net.haojinlong.codecreator.dao.inter.SysCodeTextDao;
import net.haojinlong.codecreator.service.inter.SysCodeNameService;
import net.haojinlong.commons.entity.CodeText;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郝金隆
 * @since 2014年12月7日
 */
@Service
public class SysCodeNameServiceImpl implements SysCodeNameService {
	static Logger logger = LoggerFactory
			.getLogger(SysCodeNameServiceImpl.class);

	@Autowired
	private SysCodeTextDao sysCodeTextDao;

	// key： tableName
	// value： Map<String, CodeText>, key: columnName
	private static Map<String, Map<String, List<CodeText>>> list = null;

	// key: tableName
	// value: Map<String, Map<String, String>>, key:columnName
	// ////////value: Map<String, String>, key:code, value: text
	private static Map<String, Map<String, Map<Integer, String>>> map = null;

	/**
	 * 单选配置的初始化
	 */
	private void init() {
		list = new Hashtable<String, Map<String, List<CodeText>>>();
		map = new Hashtable<String, Map<String, Map<Integer, String>>>();

		List<SysCodeText> sysList = sysCodeTextDao.listAll();
		for (SysCodeText sysCodeText : sysList) {
			String tableName = sysCodeText.getTableName();
			String columnName = sysCodeText.getColumnName();

			// --------------------------------------
			// 设置list信息
			// --------------------------------------

			// 按照表获取信息
			Map<String, List<CodeText>> tableList = list.get(tableName);
			if (tableList == null) {
				tableList = new Hashtable<String, List<CodeText>>();
				list.put(sysCodeText.getTableName(), tableList);
			}

			// 获取对应字段信息
			List<CodeText> columnList = tableList.get(columnName);
			if (columnList == null) {
				columnList = new ArrayList<CodeText>();
				tableList.put(sysCodeText.getColumnName(), columnList);
			}

			// 对应的每个配置
			CodeText codeText = new CodeText();
			codeText.setCode(sysCodeText.getCode());
			codeText.setText(sysCodeText.getText());
			columnList.add(codeText);

			// --------------------------------------
			// 设置map信息
			// --------------------------------------

			// 按照表获取信息
			Map<String, Map<Integer, String>> tableMap = map.get(tableName);
			if (tableMap == null) {
				tableMap = new Hashtable<String, Map<Integer, String>>();
				map.put(tableName, tableMap);
			}
			// 按照列名获取信息
			Map<Integer, String> columnMap = tableMap.get(columnName);
			if (columnMap == null) {
				columnMap = new Hashtable<Integer, String>();
				tableMap.put(columnName, columnMap);
			}
			columnMap.put(sysCodeText.getCode(), sysCodeText.getText());

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.SysCodeNameService#list(java
	 * .lang.String, java.lang.String)
	 */
	public List<CodeText> list(String tableName, String columnName) {
		if (map == null || list == null) {
			init();
		}
		return list.get(tableName).get(columnName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.SysCodeNameService#getMap(java
	 * .lang.String, java.lang.String)
	 */
	public Map<Integer, String> getMap(String tableName, String columnName) {
		if (map == null || list == null) {
			init();
		}
		return map.get(tableName).get(columnName);
	}

}
