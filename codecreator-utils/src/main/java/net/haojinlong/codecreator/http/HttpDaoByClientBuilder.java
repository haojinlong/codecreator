/**
 * # HttpDaoBuilder.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.http;

import net.haojinlong.codecreator.commons.entity.TableInfo;
import net.haojinlong.codecreator.commons.http.DefaultParam;
import net.haojinlong.codecreator.commons.http.DefaultResult;
import net.haojinlong.tools.encrypt.BasicEncryption;
import net.haojinlong.tools.json.JsonUtils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
public class HttpDaoByClientBuilder {
	static Logger logger = LoggerFactory
			.getLogger(HttpDaoByClientBuilder.class);
	// basicDao生成请求地址
	static String BASIC_DAO_ADDR = "http://www.haojinlong.net/codecreator/client/dao/basic";
	// Dao生成请求地址
	static String DAO_ADDR = "http://www.haojinlong.net/codecreator/client/dao/dao";

	/**
	 * 生成基础DAO接口类内容
	 * 
	 * @param packageName
	 *            包名
	 * @return 基础接口类内容
	 */
	public static DefaultResult buildBasicDao(String packageName) {
		// 参数初始化
		DefaultParam param = new DefaultParam();
		param.setPackageName(packageName);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(BASIC_DAO_ADDR);
		httpPost.setEntity(HttpUtils.formatParam(param));

		// 执行并解析请求
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity, Consts.UTF_8);
			logger.debug("json: {}", json);
			return JsonUtils.fromJson(BasicEncryption.decrypt(json), DefaultResult.class);
		} catch (Exception e) {
			DefaultResult result = new DefaultResult();
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage());
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
			return result;
		}
	}

	/**
	 * 生成DAO接口类内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return DAO接口类内容
	 */
	public static DefaultResult buildDao(String packageName, TableInfo tableInfo) {
		// 初始化参数信息
		DefaultParam param = new DefaultParam();
		param.setPackageName(packageName);
		param.setTableInfoJson(JsonUtils.toJson(tableInfo));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(DAO_ADDR);
		httpPost.setEntity(HttpUtils.formatParam(param));

		// 执行请求并解析
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity, Consts.UTF_8);
			logger.debug("json: {}", json);
			return JsonUtils.fromJson(BasicEncryption.decrypt(json), DefaultResult.class);
		} catch (Exception e) {
			DefaultResult result = new DefaultResult();
			result.setCode(DefaultResult.ERROR_CODE);
			result.setMsg(e.getMessage());
			logger.error("error: {}\n{}\n", e.getMessage(), e.getStackTrace());
			return result;
		}
	}

	// public static void main(String[] args) {
	// System.out.println(buildBasicDao("net.haojinlong").getResultStr());
	// TableInfo tableInfo = new TableInfo();
	// tableInfo.setTableName("user_addr");
	// System.out
	// .println(buildDao("net.haojinlong", tableInfo).getResultStr());
	// }

}
