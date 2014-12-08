/**
 * # TableUtils.java -- (2014年10月31日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.haojinlong.codecreator.commons.entity.ColumnInfo;
import net.haojinlong.codecreator.commons.entity.FieldInfo;
import net.haojinlong.codecreator.commons.entity.TableInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 表格读取的工具类
 * 
 * @author 郝金隆
 * @since 2014年10月31日
 *
 */
public class TableUtils {
	static Logger logger = LoggerFactory.getLogger(TableUtils.class);

	/**
	 * 读取表信息
	 * 
	 * @param tableName
	 *            表名
	 * @param conn
	 *            数据库连接
	 * @return 表信息
	 */
	public static TableInfo readTable(String tableName, Connection conn) {
		// 获取主键信息
		Set<String> set = new HashSet<String>();
		ResultSet rs;
		try {
			rs = conn.getMetaData().getPrimaryKeys(null, null, tableName);
			while (rs.next()) {
				set.add(rs.getString("COLUMN_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		TableInfo result = new TableInfo();
		result.setTableName(tableName);
		List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
		Statement stmt;

		// 获取列信息
		try {
			stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery("select * from " + tableName
					+ " where 1=2");
			ResultSetMetaData rsmd = rs2.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setColumnName(rsmd.getColumnName(i));
				columnInfo.setColumnType(rsmd.getColumnTypeName(i));
				columnInfo.setFieldInfo(new FieldInfo());
				columnInfo.setDefaultJavaType(rsmd.getColumnClassName(i));
				columnInfo.setAutoIncrement(rsmd.isAutoIncrement(i));
				if (set.contains(columnInfo.getColumnName())) {
					columnInfo.setPrimaryKey(true);
				}
				columnList.add(columnInfo);
			}
			rs2.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result.setColumnList(columnList);

		return result;
	}
}
