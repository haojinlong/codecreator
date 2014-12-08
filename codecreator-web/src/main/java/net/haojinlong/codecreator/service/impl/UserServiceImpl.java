/**
 * # UserServiceImpl.java -- (2014年11月30日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.haojinlong.codecreator.dao.entity.RoleModule;
import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.dao.entity.UserModule;
import net.haojinlong.codecreator.dao.entity.UserRole;
import net.haojinlong.codecreator.dao.inter.RoleModuleDao;
import net.haojinlong.codecreator.dao.inter.UserDao;
import net.haojinlong.codecreator.dao.inter.UserModuleDao;
import net.haojinlong.codecreator.dao.inter.UserRoleDao;
import net.haojinlong.codecreator.service.entity.PermissionEntity;
import net.haojinlong.codecreator.service.inter.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郝金隆
 * @since 2014年11月30日
 */
@Service
public class UserServiceImpl implements UserService {
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private UserModuleDao userModuleDao;

	@Autowired
	private RoleModuleDao roleModuleDao;

	@Autowired
	private UserDao userDao;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.UserService#listPermissionEntitie
	 * (int)
	 */
	public List<PermissionEntity> listPermissionEntitie(int userId) {
		List<PermissionEntity> result = new ArrayList<PermissionEntity>();

		// 获取用户对应的角色列表
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		List<UserRole> userRoles = userRoleDao.listByEntity(userRole);

		// 获取每个角色对应的权限列表
		for (UserRole ur : userRoles) {
			RoleModule roleModule = new RoleModule();
			roleModule.setRoleId(ur.getRoleId());
			List<RoleModule> roleModules = roleModuleDao
					.listByEntity(roleModule);
			for (RoleModule rm : roleModules) {
				result.add(new PermissionEntity(rm.getModuleCode(), rm
						.getOperCode()));
			}
		}

		// 获取用户权限列表
		UserModule userModule = new UserModule();
		userModule.setUserId(userId);
		List<UserModule> userModules = userModuleDao.listByEntity(userModule);
		for (UserModule um : userModules) {
			result.add(new PermissionEntity(um.getModuleCode(), um
					.getOperCode()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.UserService#getById(int)
	 */
	public User getById(int id) {
		return userDao.selectById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.UserService#listAllAvailable()
	 */
	public List<User> listAllAvailable() {
		User user = new User();
		user.setIsAvailable(true);
		return userDao.listByEntity(user);
	}

}
