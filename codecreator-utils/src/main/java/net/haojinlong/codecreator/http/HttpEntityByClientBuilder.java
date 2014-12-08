/**
 * # HttpEntityByClientBuilder.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.http;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;
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
public class HttpEntityByClientBuilder {
	static Logger logger = LoggerFactory
			.getLogger(HttpEntityByClientBuilder.class);

	// 基础实体类内容生成地址
	static String BASIC_ENTITY_ADDR = "http://www.haojinlong.net/codecreator/client/entity/basic";

	// 实体类内容生成地址
	static String ENTITY_ADDR = "http://www.haojinlong.net/codecreator/client/entity/entity";

	/**
	 * 生成基础映射类内容
	 * 
	 * @param packageName
	 *            包名
	 * @return 基础映射类内容
	 */
	public static DefaultResult buildBasicEntity(String packageName) {
		// 初始化参数
		DefaultParam param = new DefaultParam();
		param.setPackageName(packageName);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(BASIC_ENTITY_ADDR);
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
	 * 生成实体类内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return 实体类内容
	 */
	public static DefaultResult buildEntity(DbType dbType, DaoType daoType,
			String packageName, TableInfo tableInfo, String source) {
		// 初始化参数信息
		DefaultParam param = new DefaultParam();
		param.setPackageName(packageName);
		param.setTableInfoJson(JsonUtils.toJson(tableInfo));
		param.setSource(source);
		param.setDaoType(daoType);
		param.setDbType(dbType);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(ENTITY_ADDR);
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
	// System.out.println(buildBasicEntity("net.haojinlong").getResultStr());
	// System.out.println();
	//
	// // 初始化tableinfo
	// TableInfo tableInfo = new TableInfo();
	// tableInfo.setTableName("user_addr");
	// ColumnInfo columnInfo = new ColumnInfo();
	// columnInfo.setColumnName("id");
	// columnInfo.setColumnType("INT");
	// ColumnInfo columnInfo2 = new ColumnInfo();
	// columnInfo2.setColumnName("last_name");
	// columnInfo2.setColumnType("VARCHAR");
	// List<ColumnInfo> list = new ArrayList<ColumnInfo>();
	// list.add(columnInfo);
	// list.add(columnInfo2);
	// tableInfo.setColumnList(list);
	// System.out.println(buildEntity(DbType.mysql, DaoType.mybatis,
	// "net.haojinlong", tableInfo, null).getResultStr());
	//
	// }
}
