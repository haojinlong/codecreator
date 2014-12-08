/**
 * # MapperXmlFromClientController.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.client;

import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.commons.http.DefaultParam;
import net.haojinlong.codecreator.commons.http.DefaultResult;
import net.haojinlong.codecreator.core.builder.DefaultMapperXmlBuilder;
import net.haojinlong.tools.encrypt.BasicEncryption;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
@Controller
@RequestMapping("/client/mapper")
public class MapperXmlByClientController {
	static Logger logger = LoggerFactory
			.getLogger(MapperXmlByClientController.class);

	/**
	 * 生成mapper xml配置文件
	 * 
	 * @param param
	 *            传入的参数封装
	 * @return mapper XML配置文件内容
	 */
	@RequestMapping(value = "mapper", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String buildMapperXml(DefaultParam param) {
		logger.debug("param: {}", param);
		DefaultResult result = new DefaultResult();
		if (param == null || param.getDbType() == null
				|| param.getPackageName() == null
				|| param.getTableInfoJson() == null) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("缺少参数：dbType, packageName, tableInfoJson均不可为空");
			return JsonUtils.toJson(result);
		}

		if (param.getDbType() != DbType.mysql) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("目前仅支撑mysql数据库，oracle等数据库的支持仍在建设中");
			return JsonUtils.toJson(result);
		}

		try {
			TableInfo tableInfo = JsonUtils.fromJson(param.getTableInfoJson(),
					TableInfo.class);
			if (param.getSource() == null) {
				result.setResultStr(new DefaultMapperXmlBuilder(param
						.getDbType()).buildMapper(param.getPackageName(),
						tableInfo));
			} else {
				result.setResultStr(new DefaultMapperXmlBuilder(param
						.getDbType()).buildMapper(param.getPackageName(),
						tableInfo, param.getSource()));
			}
			result.setCode(DefaultResult.SUCCESS_CODE);
		} catch (Exception e) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage() + "\n" + e.getStackTrace());
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
		}
		return BasicEncryption.encrypt(JsonUtils.toJson(result));
	}
}
