/**
 * # TableServiceImpl.java -- (2014年12月5日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.haojinlong.codecreator.commons.builder.NameBuilder;
import net.haojinlong.codecreator.dao.entity.ColumnInfo;
import net.haojinlong.codecreator.dao.entity.DatabaseInfo;
import net.haojinlong.codecreator.dao.entity.IndexInfo;
import net.haojinlong.codecreator.dao.entity.SysColumnType;
import net.haojinlong.codecreator.dao.entity.TableInfo;
import net.haojinlong.codecreator.dao.inter.ColumnInfoDao;
import net.haojinlong.codecreator.dao.inter.DatabaseInfoDao;
import net.haojinlong.codecreator.dao.inter.IndexInfoDao;
import net.haojinlong.codecreator.dao.inter.SysColumnTypeDao;
import net.haojinlong.codecreator.dao.inter.TableInfoDao;
import net.haojinlong.codecreator.service.inter.TableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 郝金隆
 * @since 2014年12月5日
 */
@Service
@Transactional
public class TableServiceImpl implements TableService {
	static Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

	@Autowired
	private TableInfoDao tableInfoDao;

	@Autowired
	private DatabaseInfoDao dbInfoDao;

	@Autowired
	private SysColumnTypeDao sysColumnTypeDao;

	@Autowired
	private ColumnInfoDao columnInfoDao;

	@Autowired
	private IndexInfoDao indexInfoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#listTables(int)
	 */
	public List<TableInfo> listTables(int dbId) {
		DatabaseInfo dbInfo = dbInfoDao.selectById(dbId);
		if (dbInfo == null) {
			return null;
		}
		TableInfo tableInfo = new TableInfo();
		tableInfo.setDbId(dbId);
		tableInfo.setVersion(-1);
		tableInfo.set_orderBy(TableInfo.FIELD_IDX_ASC);
		List<TableInfo> result = tableInfoDao.listByEntity(tableInfo);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#listTables(int,
	 * int)
	 */
	public List<TableInfo> listTables(int dbId, int dbVersion) {
		// TODO 需要修改，此方法不对
		DatabaseInfo dbInfo = dbInfoDao.selectById(dbId);
		if (dbInfo == null) {
			return null;
		}
		TableInfo tableInfo = new TableInfo();
		tableInfo.setDbId(dbId);
		tableInfo.setUpdateDbVersion(dbVersion);
		tableInfo.set_orderBy(TableInfo.FIELD_IDX_ASC);
		List<TableInfo> result = tableInfoDao.listByEntity(tableInfo);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.TableService#count(int,
	 * java.lang.String)
	 */
	public int count(int dbId, String tableName) {
		TableInfo tableInfo = new TableInfo();
		tableInfo.setDbId(dbId);
		tableInfo.setName(tableName.toLowerCase());
		tableInfo.setVersion(-1);
		return tableInfoDao.countByEntity(tableInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#listColumnType()
	 */
	public List<SysColumnType> listColumnType() {
		return sysColumnTypeDao.listAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#add(net.haojinlong
	 * .codecreator.dao.entity.TableInfo, int, java.util.List)
	 */
	@Transactional
	public TableInfo add(TableInfo tableInfo, int dbId,
			List<ColumnInfo> columnList) {
		// 查询当前数据库表格数量
		DatabaseInfo dbInfo = dbInfoDao.selectById(dbId);
		int tableIdx = dbInfo.getLastTableIdx();

		// 保存表信息
		tableInfo.setName(tableInfo.getName().toLowerCase());
		tableInfo.setDbId(dbId);
		tableInfo.setCreateTime(new Date());
		tableInfo.setUpdateTime(new Date());
		tableInfo.setCreateDbVersion(dbInfo.getVersion());
		tableInfo.setUpdateDbVersion(dbInfo.getVersion());
		tableInfo.setVersion(-1);
		tableInfo.setIdx(tableIdx);
		tableInfo.setLastColumnIdx(columnList.size());
		tableInfo.setLastIndexIdx(0);
		tableInfoDao.insert(tableInfo);

		// dbInfo.count +=1
		dbInfo.setLastTableIdx(tableIdx + 1);
		dbInfoDao.update(dbInfo);

		// 保存列信息
		for (int i = 0; i < columnList.size(); i++) {
			ColumnInfo columnInfo = columnList.get(i);
			columnInfo.setName(columnInfo.getName().toLowerCase());
			columnInfo.setTableId(tableInfo.getId());
			columnInfo.setIdx(i);
			columnInfoDao.insert(columnInfo);
		}
		return tableInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#listCurrentColumnInfo
	 * (int)
	 */
	public List<ColumnInfo> listCurrentColumnInfo(int tableId) {
		ColumnInfo columnInfo = new ColumnInfo();
		columnInfo.setTableId(tableId);
		columnInfo.set_orderBy(ColumnInfo.FIELD_IDX_ASC);
		return columnInfoDao.listByEntity(columnInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#getTableInfo(int)
	 */
	public TableInfo getTableInfo(int id) {
		return tableInfoDao.selectById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#listColumnTypeName
	 * ()
	 */
	public List<String> listColumnTypeName() {
		List<SysColumnType> typeList = sysColumnTypeDao.listAll();
		if (typeList.size() == 0) {
			return new ArrayList<String>();
		}

		// 获取最大的code值
		int maxCode = 0;
		for (SysColumnType columnType : typeList) {
			maxCode = columnType.getCode() > maxCode ? columnType.getCode()
					: maxCode;
		}

		// 默认赋值
		List<String> result = new ArrayList<String>();
		for (int i = 0; i <= maxCode; i++) {
			result.add("");
		}

		// 根据配置数据赋值
		for (SysColumnType columnType : typeList) {
			result.set(columnType.getCode(), columnType.getName());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#updateTable(net
	 * .haojinlong.codecreator.dao.entity.TableInfo, java.util.List,
	 * java.util.List)
	 */
	@Transactional
	public void updateTable(TableInfo tableInfo,
			List<ColumnInfo> existColumnList,
			List<ColumnInfo> additionColumnList) {

		// - -- - -------------------------------------------------
		// 处理表信息
		// --------------------------------------------------------
		TableInfo dbTableInfo = tableInfoDao.selectById(tableInfo.getId());
		dbTableInfo.setName(tableInfo.getName().toLowerCase());
		dbTableInfo.setDescription(tableInfo.getDescription());
		dbTableInfo.setUpdateDbVersion(dbInfoDao.selectById(
				dbTableInfo.getDbId()).getVersion());
		dbTableInfo.setUpdateTime(new Date());
		int columnIdx = dbTableInfo.getLastColumnIdx();
		if (additionColumnList != null && additionColumnList.size() > 0) {
			dbTableInfo.setLastColumnIdx(columnIdx + additionColumnList.size());
		}
		tableInfoDao.update(dbTableInfo);

		// --------------------------------------------------------
		// 处理修改的列信息
		// --------------------------------------------------------
		// 初始化HashMap
		Map<Integer, ColumnInfo> columnMap = new HashMap<Integer, ColumnInfo>();
		for (ColumnInfo columnInfo : existColumnList) {
			columnMap.put(columnInfo.getIdx(), columnInfo);
		}
		// 获取已有列信息
		ColumnInfo columnInfo = new ColumnInfo();
		columnInfo.setTableId(tableInfo.getId());
		columnInfo.set_orderBy(ColumnInfo.FIELD_IDX_ASC);
		List<ColumnInfo> columnList = columnInfoDao.listByEntity(columnInfo);
		IndexInfo indexInfo = new IndexInfo();
		indexInfo.setTableId(tableInfo.getId());
		List<IndexInfo> indexList = indexInfoDao.listByEntity(indexInfo);
		// 对现有列信息进行处理
		for (ColumnInfo info : columnList) {
			ColumnInfo updateInfo = columnMap.get(info.getIdx());
			if (updateInfo == null) {
				columnInfoDao.delete(info);
				for (IndexInfo idxInfo : indexList) {
					List<String> columnNameList = Arrays.asList(idxInfo
							.getContent().split(","));
					if (columnNameList.contains(info.getName())) {
						indexInfoDao.delete(idxInfo);
					}
				}

			} else {
				info.setName(updateInfo.getName().toLowerCase());
				info.setDescription(updateInfo.getDescription());
				info.setTypeCode(updateInfo.getTypeCode());
				info.setSize(updateInfo.getSize());
				columnInfoDao.update(info);
			}
		}

		// ------------------------------------------------------
		// 添加列
		// ------------------------------------------------------
		if (additionColumnList != null && additionColumnList.size() > 0) {
			for (int i = 0; i < additionColumnList.size(); i++) {
				ColumnInfo info = additionColumnList.get(i);
				info.setName(info.getName().toLowerCase());
				info.setIdx(columnIdx + i);
				info.setTableId(tableInfo.getId());
				columnInfoDao.insert(info);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.TableService#delete(int,
	 * int)
	 */
	@Transactional
	public void delete(int dbId, int tableId) {
		TableInfo tableInfo = tableInfoDao.selectById(tableId);
		if (tableInfo.getDbId() == dbId) {
			// 删除列信息
			ColumnInfo columnInfo = new ColumnInfo();
			columnInfo.setTableId(tableId);
			columnInfoDao.deleteByEntity(columnInfo);

			// 删除index信息
			IndexInfo indexInfo = new IndexInfo();
			indexInfo.setTableId(tableId);
			indexInfoDao.deleteByEntity(indexInfo);

			// 删除表信息
			tableInfoDao.delete(tableInfo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.TableService#listIndex(int)
	 */
	public List<IndexInfo> listIndex(int tableId) {
		IndexInfo indexInfo = new IndexInfo();
		indexInfo.setTableId(tableId);
		return indexInfoDao.listByEntity(indexInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.TableService#updateIndex(int,
	 * java.util.List, java.util.List)
	 */
	@Transactional
	public void updateIndex(int tableId, List<IndexInfo> indexList,
			List<IndexInfo> additionIndexList) {

		// 处理已有的index信息
		Set<Integer> idSet = new HashSet<Integer>();
		Set<String> nameSet = new HashSet<String>();
		if (indexList != null) {
			for (IndexInfo info : indexList) {
				idSet.add(info.getId());
			}
		}
		IndexInfo indexInfo = new IndexInfo();
		indexInfo.setTableId(tableId);
		List<IndexInfo> dbIndexList = indexInfoDao.listByEntity(indexInfo);
		for (IndexInfo info : dbIndexList) {
			if (!idSet.contains(info.getId())) {
				indexInfoDao.delete(info);
			} else {
				nameSet.add(info.getName());
			}
		}

		// 添加新的索引信息
		if (additionIndexList != null) {
			TableInfo tableInfo = tableInfoDao.selectById(tableId);
			for (IndexInfo info : additionIndexList) {
				if (info.getContent() != null && info.getContent().length() > 0) {
					info.setTableId(tableId);
					StringBuffer name = new StringBuffer("idx_"
							+ NameBuilder.buildEntityName(tableInfo.getName())
									.toLowerCase());
					for (String columnName : info.getContent().split(",")) {
						name.append("_"
								+ NameBuilder.buildFieldName(columnName)
										.toLowerCase());
					}

					if (!nameSet.contains(name.toString())) {
						info.setName(name.toString());
						indexInfoDao.insert(info);
						nameSet.add(name.toString());
					}

				}
			}
		}
	}

}
