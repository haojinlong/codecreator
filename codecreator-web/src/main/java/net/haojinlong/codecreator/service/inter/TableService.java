/**
 * # TableService.java -- (2014年12月5日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.inter;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.ColumnInfo;
import net.haojinlong.codecreator.dao.entity.IndexInfo;
import net.haojinlong.codecreator.dao.entity.TableInfo;

/**
 * @author 郝金隆
 * @since 2014年12月5日
 */
public interface TableService {

	/**
	 * 根据数据库id获取最新的数据库列表
	 * 
	 * @param databseId
	 *            数据库id
	 * @return 当前数据库中的表信息
	 */
	public List<TableInfo> listTables(int databseId);

	/**
	 * 根据数据库id和表名获取
	 * 
	 * @param databaseId
	 * @param dbVersion
	 * @return
	 */
	public List<TableInfo> listTables(int databaseId, int dbVersion);

	/**
	 * 获取数据库中此表名的库表数量
	 * 
	 * @param dbId
	 *            数据库id
	 * @param tableName
	 *            表名
	 * @return
	 */
	public int count(int dbId, String tableName);

	/**
	 * 获取所有的字段类型
	 * 
	 * @return
	 */
	//public List<SysColumnType> listColumnType();

	/**
	 * 获取所有字段的配置类型信息
	 * 
	 * @return
	 */
	//public List<String> listColumnTypeName();

	/**
	 * 添加新的数据库库表
	 * 
	 * @param tableInfo
	 *            表信息
	 * @param dbId
	 *            数据库id
	 * @param columnList
	 *            列信息
	 * @return
	 */
	public TableInfo add(TableInfo tableInfo, int dbId,
			List<ColumnInfo> columnList);

	/**
	 * 获取当前所有的列信息
	 * 
	 * @param tableId
	 *            表id
	 * @return
	 */
	public List<ColumnInfo> listCurrentColumnInfo(int tableId);

	/**
	 * 根据表id获取表格基础信息
	 * 
	 * @param id
	 *            表id
	 * @return
	 */
	public TableInfo getTableInfo(int id);

	/**
	 * 更新表信息
	 * 
	 * @param tableInfo
	 *            表信息
	 * @param existColumnList
	 *            现有的列信息
	 * @param additionColumnList
	 *            本次添加的列信息
	 */
	public void updateTable(TableInfo tableInfo,
			List<ColumnInfo> existColumnList,
			List<ColumnInfo> additionColumnList);

	/**
	 * 删除表
	 * 
	 * @param dbId
	 *            数据库id
	 * @param tableId
	 *            表id
	 */
	public void delete(int dbId, int tableId);

	/**
	 * 获取数据库中的索引列表
	 * 
	 * @param tableId
	 *            表id
	 * @return 索引列表
	 */
	public List<IndexInfo> listIndex(int tableId);

	/**
	 * 索引信息更新
	 * @param tableId
	 * @param indexList
	 * @param additionIndexList
	 */
	public void updateIndex(int tableId, List<IndexInfo> indexList,
			List<IndexInfo> additionIndexList);
}
