/**
 * # UserService.java -- (2014年11月30日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.inter;

import java.util.List;

import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.service.entity.PermissionEntity;

/**
 * @author 郝金隆
 * @since 2014年11月30日
 */
public interface UserService {

	/**
	 * 根据用户id获取用户的权限
	 * 
	 * @param userId
	 *            用户的id
	 * @return 用户的权限列表
	 */
	public List<PermissionEntity> listPermissionEntitie(int userId);
	
	/**
	 * 获取用户信息
	 * @param id 用户id
	 * @return
	 */
	public User getById(int id);
	
	
	/**
	 * 获取所有的可用用户列表
	 * @return 所有的用户列表
	 */
	public List<User> listAllAvailable();
	
	

}
