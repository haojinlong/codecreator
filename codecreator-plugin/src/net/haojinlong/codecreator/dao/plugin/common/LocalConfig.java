/**
 * # LocalConfig.java -- (2014年11月5日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.common;

import net.haojinlong.tools.json.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月5日
 *
 */
public class LocalConfig {
	static Logger logger = LoggerFactory.getLogger(LocalConfig.class);
	private SourceType sourceType;
	private CreateFromDbParam dbParam;
	private CreateFromServerParam serverParam;

	/**
	 * @return the sourceType
	 */
	public SourceType getSourceType() {
		return sourceType;
	}

	/**
	 * @return the dbParam
	 */
	public CreateFromDbParam getDbParam() {
		return dbParam;
	}

	/**
	 * @return the serverParam
	 */
	public CreateFromServerParam getServerParam() {
		return serverParam;
	}

	/**
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @param dbParam
	 *            the dbParam to set
	 */
	public void setDbParam(CreateFromDbParam dbParam) {
		this.dbParam = dbParam;
	}

	/**
	 * @param serverParam
	 *            the serverParam to set
	 */
	public void setServerParam(CreateFromServerParam serverParam) {
		this.serverParam = serverParam;
	}

	/**
	 * 根据相关信息生成配置文件内容
	 * 
	 * @return
	 */
	public String toConfigure() {
		return JsonUtils.toJson(this);
	}
}
