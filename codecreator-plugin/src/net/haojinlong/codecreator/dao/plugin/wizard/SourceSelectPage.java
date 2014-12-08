/**
 * # TypeSelectPage.java -- (2014年11月3日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import net.haojinlong.codecreator.dao.plugin.common.LocalConfig;
import net.haojinlong.codecreator.dao.plugin.common.SourceType;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月3日
 *
 */
public class SourceSelectPage extends WizardPage {
	static Logger logger = LoggerFactory.getLogger(SourceSelectPage.class);

	private SourceType sourceType;

	private Button dbRadio;
	private Button remoteRadio;
	private Button configRadio;

	private LocalConfig localConfig;

	public SourceSelectPage(LocalConfig localConfig) {
		super("类型选择");
		setTitle("选择类型");
		setDescription("请确定是从本地读取数据库生成还是从远程数据库配置生成");
		this.localConfig = localConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		container.setLayout(layout);

		Label sourceLabel = new Label(container, SWT.NULL);
		sourceLabel.setText("\n请选择从本地数据库创建还是同远程配置创建：");
		Composite sourceContainer = new Composite(container, SWT.BORDER);
		sourceContainer.setLayout(layout);
		sourceContainer.setLayoutData(gd);

		// 添加读取本地数据库选项
		dbRadio = new Button(sourceContainer, SWT.RADIO);
		dbRadio.setText("读取本地数据库配置，生成DAO文件");
		dbRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sourceType = SourceType.db;
			}
		});

		// 添加读取远程配置选项
		remoteRadio = new Button(sourceContainer, SWT.RADIO);
		remoteRadio.setText("根据haojinlong_codecreator网站上的配置生成DAO文件");
		remoteRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sourceType = SourceType.remote;
			}
		});

		// 添加使用上次相同配置选项
		configRadio = new Button(sourceContainer, SWT.RADIO | SWT.WRAP);
		configRadio.setText("使用上次相同配置（未找到上次记录）");
		configRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sourceType = SourceType.config;
			}
		});

		// 初始化
		init();
		setControl(container);
	}

	// 内容初始化，默认设置为读取本地化数据库，暂不考虑远程的支撑
	public void init() {
		// 从服务器获取不可用
		remoteRadio.setEnabled(false);

		// 根据本地化的配置进行初始化
		if (localConfig != null) {
			configRadio.setEnabled(true);
			configRadio.setSelection(true);
			sourceType = SourceType.config;
			if (localConfig.getSourceType() == SourceType.db) {
				configRadio.setText("使用上次相同配置（通过本地数据库生成）");
			}
		} else { // 未能读取到原有配置
			dbRadio.setSelection(true);
			configRadio.setEnabled(false);
			sourceType = SourceType.db;
		}
	}

	/**
	 * @return the sourceType
	 */
	public SourceType getSourceType() {
		return sourceType;
	}

}
