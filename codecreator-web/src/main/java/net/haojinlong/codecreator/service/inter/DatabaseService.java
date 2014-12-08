/**
 * # DatabaseService.java -- (2014年11月9日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.inter;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.DatabaseInfo;
import net.haojinlong.codecreator.dao.entity.User;

/**
 * @author 郝金隆
 * @since 2014年11月9日
 */
public interface DatabaseService {
	/**
	 * 获取当前用户名下的数据库数量
	 * 
	 * @return 当前用户下的数据库数量
	 */
	public int count();

	/**
	 * 根据数据库名称进行查找
	 * 
	 * @param dbName
	 *            数据库名称
	 * @return 数据条数
	 */
	public int countByDbName(String dbName);

	/**
	 * 分页获取数据库信息列表
	 * 
	 * @param page
	 *            页码，从1开始
	 * @param piecePerPage
	 *            每页条数
	 * @return 分页数据库列表
	 */
	public List<DatabaseInfo> list(int page, int piecePerPage);

	/**
	 * 分页获取数据库信息列表
	 * 
	 * @param dbName
	 *            数据库名
	 * @param page
	 *            页码，从1开始
	 * @param piecePerPage
	 *            每页条数
	 * @return 分页数据库列表
	 */
	public List<DatabaseInfo> listByDbName(String dbName, int page,
			int piecePerPage);

	/**
	 * 数据库信息保存
	 * 
	 * @param dbINfo
	 *            要保存的数据库信息
	 * @param userList
	 *            用户列表
	 * @return
	 */
	public DatabaseInfo save(DatabaseInfo dbINfo, List<Integer> userList);

	/**
	 * 获取数据库信息
	 * 
	 * @param id
	 *            数据库的id
	 * @return 数据库信息
	 */
	public DatabaseInfo get(int id);
	
	
	/**
	 * 数据库对应的用户信息列表
	 * @param databaseId 数据库id
	 * @return
	 */
	public List<User> listUserByDatabase(int databaseId);

}
