/**
 * # DataBaseServiceImpl.java -- (2014年11月9日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import net.haojinlong.codecreator.dao.entity.DatabaseInfo;
import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.dao.entity.UserDatabase;
import net.haojinlong.codecreator.dao.inter.DatabaseInfoDao;
import net.haojinlong.codecreator.dao.inter.UserDatabaseDao;
import net.haojinlong.codecreator.service.inter.DatabaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 郝金隆
 * @since 2014年11月9日
 */
@Service
public class DataBaseServiceImpl implements DatabaseService {
	static Logger logger = LoggerFactory.getLogger(DataBaseServiceImpl.class);

	@Autowired
	private DatabaseInfoDao dbInfoDao;

	@Autowired
	private UserDatabaseDao userDatabaseDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.DatabaseService#count()
	 */
	public int count() {
		return dbInfoDao.countAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.DatabaseService#countByDbName
	 * (java.lang.String)
	 */
	public int countByDbName(String dbName) {
		DatabaseInfo dbInfo = new DatabaseInfo();
		dbInfo.setName(dbName);
		return dbInfoDao.countByEntity(dbInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.DatabaseService#list( int,
	 * int)
	 */
	public List<DatabaseInfo> list(int page, int piecePerPage) {
		DatabaseInfo dbInfo = new DatabaseInfo();
		dbInfo.set_page(page);
		dbInfo.set_linesPerPage(piecePerPage);
		dbInfo.set_orderBy(DatabaseInfo.FIELD_DISABLED_ASC,
				DatabaseInfo.FIELD_LOCKED_DESC, DatabaseInfo.FIELD_ID_ASC);
		return dbInfoDao.listByEntity(dbInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.DatabaseService#listByDbName
	 * (java.lang.String, int, int)
	 */
	public List<DatabaseInfo> listByDbName(String dbName, int page,
			int piecePerPage) {
		DatabaseInfo dbInfo = new DatabaseInfo();
		dbInfo.setName(dbName);
		dbInfo.set_page(page);
		dbInfo.set_linesPerPage(piecePerPage);
		dbInfo.set_orderBy(DatabaseInfo.FIELD_DISABLED_ASC,
				DatabaseInfo.FIELD_LOCKED_DESC, DatabaseInfo.FIELD_ID_ASC);
		return dbInfoDao.listLikeEntity(dbInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.DatabaseService#save(net.haojinlong
	 * .codecreator.dao.entity.DatabaseInfo, java.util.List)
	 */
	@Transactional
	public DatabaseInfo save(DatabaseInfo dbInfo, List<Integer> userList) {
		if (countByDbName(dbInfo.getName()) > 0) {
			DatabaseInfo databaseInfo = new DatabaseInfo();
			databaseInfo.setName(dbInfo.getName());
			return dbInfoDao.listByEntity(databaseInfo).get(0);
		}

		dbInfo.setLockUserId(dbInfo.getUserId());
		dbInfo.setDisabled(false);
		dbInfo.setLocked(true);
		dbInfo.setVersion(1);
		dbInfo.setCreateTime(new Date());
		dbInfo.setUpdateTime(new Date());
		dbInfo.setDbKey(System.currentTimeMillis() + ""
				+ Math.abs(new Random().nextInt(1000)));
		dbInfo.setLastTableIdx(0);
		dbInfoDao.insert(dbInfo);
		for (int userId : userList) {
			UserDatabase userDatabase = new UserDatabase();
			userDatabase.setDatabaseId(dbInfo.getId());
			userDatabase.setUserId(userId);
			userDatabaseDao.insert(userDatabase);
		}
		return dbInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.haojinlong.codecreator.service.inter.DatabaseService#get(int)
	 */
	public DatabaseInfo get(int id) {
		return dbInfoDao.selectById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.haojinlong.codecreator.service.inter.DatabaseService#listUserByDatabase
	 * (int)
	 */
	public List<User> listUserByDatabase(int databaseId) {
		DatabaseInfo dbInfo = new DatabaseInfo();
		dbInfo.setId(databaseId);
		return userDatabaseDao.listUserByDatabaseInfo(dbInfo);
	}

}
