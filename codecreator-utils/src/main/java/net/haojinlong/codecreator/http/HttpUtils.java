/**
 * # HttpUtils.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.http;

import java.util.ArrayList;
import java.util.List;

import net.haojinlong.codecreator.commons.http.DefaultParam;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
public class HttpUtils {
	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static UrlEncodedFormEntity formatParam(DefaultParam param) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (param == null) {
			return new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		}
		if (param.getDaoType() != null) {
			formparams.add(new BasicNameValuePair("daoType", param.getDaoType()
					.toString()));
		}
		if (param.getDbType() != null) {
			formparams.add(new BasicNameValuePair("dbType", param.getDbType()
					.toString()));
		}
		if (param.getPackageName() != null) {
			formparams.add(new BasicNameValuePair("packageName", param
					.getPackageName()));
		}
		if (param.getTableInfoJson() != null) {
			formparams.add(new BasicNameValuePair("tableInfoJson", param
					.getTableInfoJson()));
		}
		if (param.getSource() != null) {
			formparams.add(new BasicNameValuePair("source", param.getSource()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		return entity;
	}
}
