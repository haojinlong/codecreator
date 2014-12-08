/**
 * # EntityCreateController.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.client;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.commons.http.DefaultParam;
import net.haojinlong.codecreator.commons.http.DefaultResult;
import net.haojinlong.codecreator.core.builder.DefaultEntityBuilder;
import net.haojinlong.codecreator.core.builder.EntityBuilder;
import net.haojinlong.tools.encrypt.BasicEncryption;
import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 读取数据库列表，通过请求内容生成映射类文件内容
 * 
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
@Controller
@RequestMapping("/client/entity")
public class EntityByClientController {
	static Logger logger = LoggerFactory
			.getLogger(EntityByClientController.class);

	/**
	 * 生成基础的实体类内容
	 * 
	 * @param param
	 *            请求内容的封装
	 * @return 基础的实体类内容
	 */
	@RequestMapping(value = "/basic", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String buildMysqlMybatisBasicEntity(DefaultParam param) {
		DefaultResult result = new DefaultResult();
		EntityBuilder entityBuilder = new DefaultEntityBuilder(DbType.mysql,
				DaoType.mybatis);
		if (param == null || param.getPackageName() == null) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("包名不可为空");
			return JsonUtils.toJson(result);
		}
		try {
			String resultStr = entityBuilder.buildBasicEntity(param
					.getPackageName());
			result.setCode(DefaultResult.SUCCESS_CODE);
			result.setResultStr(resultStr);
		} catch (Exception e) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage());
		}
		return BasicEncryption.encrypt(JsonUtils.toJson(result));
	}

	/**
	 * 生成相应的实体类内容
	 * 
	 * @param param
	 *            请求内容的封装
	 * @return 相应的实体类内容更
	 */
	@RequestMapping(value = "/entity", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String buildMysqlMybatisEntity(DefaultParam param) {
		DefaultResult result = new DefaultResult();
		if (param == null || param.getDaoType() == null
				|| param.getDbType() == null || param.getPackageName() == null
				|| param.getTableInfoJson() == null) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("缺少参数：dbType, daoType, packageName, tableInfoJson均不可为空");
			return JsonUtils.toJson(result);
		}

		if (param.getDaoType() != DaoType.mybatis
				|| param.getDbType() != DbType.mysql) {
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg("目前仅支撑mysql数据库和mybatis的映射，oracle等数据库和hibernate/jpa的支持仍在建设中");
			return JsonUtils.toJson(result);
		}

		try {
			TableInfo tableInfo = JsonUtils.fromJson(param.getTableInfoJson(),
					TableInfo.class);
			if (param.getSource() == null) {
				result.setResultStr(new DefaultEntityBuilder(param.getDbType(),
						param.getDaoType()).buildEntity(param.getPackageName(),
						tableInfo));
			} else {
				result.setResultStr(new DefaultEntityBuilder(param.getDbType(),
						param.getDaoType()).buildEntity(param.getPackageName(),
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
