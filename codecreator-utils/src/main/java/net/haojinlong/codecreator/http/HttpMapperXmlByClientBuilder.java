/**
 * # HttpMapperXmlByClientBuilder.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.http;

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
public class HttpMapperXmlByClientBuilder {
	static Logger logger = LoggerFactory
			.getLogger(HttpMapperXmlByClientBuilder.class);
	// Dao生成请求地址
	static String MAPPER_XML_ADDR = "http://www.haojinlong.net/codecreator/client/mapper/mapper";

	/**
	 * 生成mybatis的Mapper XML内容
	 * 
	 * @param packageName
	 *            包名
	 * @param tableInfo
	 *            表信息
	 * @return mybatis的Mapper XML内容
	 */
	public static DefaultResult buildMapperXml(DbType dbType,
			String packageName, TableInfo tableInfo, String source) {
		// 初始化参数信息
		DefaultParam param = new DefaultParam();
		param.setPackageName(packageName);
		param.setTableInfoJson(JsonUtils.toJson(tableInfo));
		param.setSource(source);
		param.setDbType(dbType);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(MAPPER_XML_ADDR);
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
	// System.out.println(buildMapperXml(DbType.mysql, "net.haojinlong",
	// tableInfo, null).getResultStr());
	//
	// }
}
