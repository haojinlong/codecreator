/**
 * # PermissionEntity.java -- (2014年11月30日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.service.entity;

import java.io.Serializable;

/**
 * @author 郝金隆
 * @since 2014年11月30日
 */
public class PermissionEntity implements Serializable {

	private static final long serialVersionUID = 8018631297240418807L;
	// 模块的code值
	private String module;
	// 操作类型
	private String operType;

	public PermissionEntity(String module, String operType) {
		super();
		this.module = module;
		this.operType = operType;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * @param operType
	 *            the operType to set
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

}
