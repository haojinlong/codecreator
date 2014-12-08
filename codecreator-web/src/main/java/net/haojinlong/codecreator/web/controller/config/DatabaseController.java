/**
 * # DatabaseController.java -- (2014年11月9日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.config;

import java.util.ArrayList;
import java.util.List;

import net.haojinlong.codecreator.dao.entity.DatabaseInfo;
import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.service.inter.DatabaseService;
import net.haojinlong.codecreator.service.inter.UserService;
import net.haojinlong.commons.web.entity.Pager;
import net.haojinlong.commons.webauth.anno.Authority;
import net.haojinlong.commons.webauth.consts.OperType;
import net.haojinlong.commons.webauth.entity.UserProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author 郝金隆
 * @since 2014年11月9日
 */
@Controller
@RequestMapping("/config/database")
@SessionAttributes("userProps")
public class DatabaseController {
	static Logger logger = LoggerFactory.getLogger(DatabaseController.class);
	static int PIECE_PER_PAGE = 20;

	@Autowired
	private DatabaseService dbService;

	@Autowired
	private UserService userService;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "redirect:/config/database/list/1";
	}

	/**
	 * 列表页面
	 * 
	 * @param model
	 * @param pageStr
	 * @return
	 */
	@RequestMapping("/list/{pageStr}")
	@Authority(module = "database", operType = OperType.list)
	public String list(ModelMap model, @PathVariable("pageStr") String pageStr) {
		// 分页
		int count = dbService.count();
		int page = 1;
		if (pageStr != null) {
			try {
				page = Integer.parseInt(pageStr);
			} catch (Exception e) {
				return "redirect:/config/database/list/1";
			}
		}
		Pager pager = new Pager(count, PIECE_PER_PAGE, page);
		model.addAttribute("pager", pager);

		// 列表
		List<DatabaseInfo> list;
		if (count == 0) {
			list = new ArrayList<DatabaseInfo>();
		} else {
			list = dbService.list(page, PIECE_PER_PAGE);
		}
		model.addAttribute("list", list);
		logger.debug("list: {}", list);

		return "/config/database/list";
	}

	@RequestMapping("/query/{dbName}/{pageStr}")
	@Authority(module = "database", operType = OperType.list)
	public String query(ModelMap model, @PathVariable("dbName") String dbName,
			@PathVariable("pageStr") String pageStr) {
		return null;
	}

	@RequestMapping("/add/validate")
	@ResponseBody
	@Authority(module = "database", operType = OperType.add)
	public String valiateName(String name, ModelMap model) {
		int count = dbService.countByDbName(name);
		if (count == 0) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping("/add/to")
	@Authority(module = "database", operType = OperType.add)
	public String toAdd(ModelMap model) {
		model.addAttribute("userList", userService.listAllAvailable());
		return "/config/database/add";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/add/do")
	@Authority(module = "database", operType = OperType.add)
	public String doAdd(DatabaseInfo databaseInfo,
			@RequestParam("userList") List<Integer> userList, ModelMap model) {
		if (logger.isDebugEnabled()) {
			logger.debug("addDatabaseForm: {}", databaseInfo);
			logger.debug("userList:{}", userList);
		}
		UserProps<User> userProps = (UserProps<User>) model.get("userProps");
		databaseInfo.setUserId(userProps.getUserInfo().getId());
		DatabaseInfo dbInfoSaved = dbService.save(databaseInfo, userList);
		return "redirect:/config/database/view/" + dbInfoSaved.getId();
	}

	
	@RequestMapping("/view/{id}")
	@Authority(module = "database", operType = OperType.view, forceValidateId=true)
	public String view(@PathVariable("id") int id, ModelMap model) {
		DatabaseInfo dbInfo = dbService.get(id);
		if (dbInfo != null) {
			model.addAttribute("dbInfo", dbInfo);
		}
		User owner = userService.getById(dbInfo.getUserId());
		model.addAttribute("owner", owner);
		model.addAttribute("userList", dbService.listUserByDatabase(dbInfo.getId()));
		return "/config/database/view";
	}
}
