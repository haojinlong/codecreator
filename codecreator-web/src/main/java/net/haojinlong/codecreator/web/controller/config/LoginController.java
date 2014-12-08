/**
 * # LoginController.java -- (2014年11月30日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.haojinlong.codecreator.dao.entity.User;
import net.haojinlong.codecreator.service.entity.PermissionEntity;
import net.haojinlong.codecreator.service.inter.UserService;
import net.haojinlong.commons.webauth.entity.UserProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author 郝金隆
 * @since 2014年11月30日
 */
@Controller
@RequestMapping("/config")
@SessionAttributes("userProps")
public class LoginController {
	static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/login/{userId}")
	public String login(ModelMap model, @PathVariable("userId") int userId) {
		User user = userService.getById(userId);
		if (user != null) {
			UserProps<User> userProps = new UserProps<User>();
			userProps.setUserInfo(user);
			userProps.setUserId(userId);
			List<PermissionEntity> list = userService
					.listPermissionEntitie(userId);
			for (PermissionEntity pe : list) {
				userProps.addAuth(pe.getModule(), pe.getOperType());
			}
			model.addAttribute("userProps", userProps);
		}
		return "redirect:/config/database/list/1";
	}

	@RequestMapping("/logout")
	public String logout(ModelMap model, HttpSession session) {
		model.remove("userProps");
		session.removeAttribute("userProps");
		return "redirect:/";
	}
}
