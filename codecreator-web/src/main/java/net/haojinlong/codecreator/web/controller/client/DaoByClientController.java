/**
 * # DaoFromClientController.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.client;

import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.commons.http.DefaultParam;
import net.haojinlong.codecreator.commons.http.DefaultResult;
import net.haojinlong.codecreator.core.builder.DefaultDaoBuilder;
import net.haojinlong.tools.encrypt.BasicEncryption;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 读取数据库表，生成OR映射数据库读写操作的接口类内容
 * 
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
@Controller
@RequestMapping("/client/dao")
public class DaoByClientController {
	static Logger logger = LoggerFactory
			.getLogger(DaoByClientController.class);

	/**
	 * 生成基础的接口类
	 * 
	 * @param param
	 *            请求内容的封装
	 * @return 基础接口类内容
	 */
	@RequestMapping(value = "/basic", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String buildBasicDao(DefaultParam param) {
		logger.debug("param: {}", param);
		DefaultResult result = new DefaultResult();
		if (param == null || param.getPackageName() == null) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("包名不可为空");
			return JsonUtils.toJson(result);
		}
		try {
			result.setCode(DefaultResult.SUCCESS_CODE);
			result.setResultStr(new DefaultDaoBuilder().buildBasicDao(param
					.getPackageName()));
		} catch (Exception e) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage());
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		}
		return BasicEncryption.encrypt(JsonUtils.toJson(result));
	}

	/**
	 * 生成对应的接口类
	 * 
	 * @param param
	 *            请求内容的封装
	 * @return 接口类内容
	 */
	@RequestMapping(value = "/dao", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String buildDao(DefaultParam param) {
		logger.debug("param: {}", param);
		DefaultResult result = new DefaultResult();

		if (param == null || param.getPackageName() == null
				|| param.getTableInfoJson() == null) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("缺少参数：packageName, tableInfoJson均不可为空");
			return JsonUtils.toJson(result);
		}

		try {
			TableInfo tableInfo = JsonUtils.fromJson(param.getTableInfoJson(),
					TableInfo.class);
			result.setResultStr(new DefaultDaoBuilder().buildDao(
					param.getPackageName(), tableInfo));
			result.setCode(DefaultResult.SUCCESS_CODE);
		} catch (Exception e) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage() + "\n" + e.getStackTrace());
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		}
		return BasicEncryption.encrypt(JsonUtils.toJson(result));
	}

}
