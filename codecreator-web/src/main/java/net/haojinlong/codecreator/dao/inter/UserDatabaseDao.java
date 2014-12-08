/**
 * # UserDatabaseDao.java -- (2014-11-30)
 * 作者：郝金隆(www.haojinlong.net) -- 代码生成器自动生成
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.inter;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.DatabaseInfo;
import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.dao.entity.UserDatabase;

/**
 * @author 郝金隆【自动生成】
 * @since 2014-11-30
 *
 */
public interface UserDatabaseDao extends _Dao<UserDatabase> {
	
	/**
	 * 根据用户信息获取数据库列表
	 * @param user 用户信息等
	 * @return
	 */
	public List<DatabaseInfo> listDbByUser(User user);
	
	
	/**
	 * 根据数据库信息获取
	 * @param databaseInfo
	 * @return
	 */
	public List<User> listUserByDatabaseInfo(DatabaseInfo databaseInfo);
}