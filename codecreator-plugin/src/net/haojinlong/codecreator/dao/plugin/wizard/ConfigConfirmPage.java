/**
 * # ConfigConfirmPage.java -- (2014年11月6日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import net.haojinlong.codecreator.dao.plugin.common.LocalConfig;
import net.haojinlong.codecreator.dao.plugin.common.SourceType;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月6日
 *
 */
public class ConfigConfirmPage extends WizardPage {
	static Logger logger = LoggerFactory.getLogger(ConfigConfirmPage.class);

	LocalConfig localConfig;

	Label sourceTypeLabel;

	public ConfigConfirmPage(LocalConfig localConfig) {
		super("configConfirm");
		setTitle("确定当前配置");
		setDescription("请确认上一次的配置！");
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
		Layout layout = new GridLayout(1, false);
		container.setLayout(layout);
		sourceTypeLabel = new Label(container, SWT.NULL);

		if (localConfig != null) {
			Composite configContainer = new Composite(container, SWT.BORDER);
			Layout configLayout = new GridLayout(2, false);
			configContainer.setLayout(configLayout);
			displayConfig(localConfig, configContainer);
		}

		setControl(container);
	}

	private void displayConfig(LocalConfig localConfig, Composite parent) {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		if (localConfig.getSourceType() == SourceType.db) {
			new Label(parent, SWT.NULL).setText("驱动程序地址：");
			Text jarText = new Text(parent, SWT.BORDER | SWT.WRAP);
			jarText.setText(localConfig.getDbParam().getJarDir());
			jarText.setLayoutData(gd);
			jarText.setEditable(false);

			new Label(parent, SWT.NULL).setText("DriverName：");
			Text driverNameText = new Text(parent, SWT.BORDER | SWT.WRAP);
			driverNameText.setText(localConfig.getDbParam().getDriverName());
			driverNameText.setLayoutData(gd);
			driverNameText.setEditable(false);

			new Label(parent, SWT.NULL).setText("连接地址：");
			Text urlText = new Text(parent, SWT.BORDER | SWT.WRAP);
			urlText.setText(localConfig.getDbParam().getUrl());
			urlText.setLayoutData(gd);
			urlText.setEditable(false);

			new Label(parent, SWT.NULL).setText("用户名：");
			Text usernameText = new Text(parent, SWT.BORDER | SWT.WRAP);
			usernameText.setText(localConfig.getDbParam().getUsername());
			usernameText.setLayoutData(gd);
			usernameText.setEditable(false);

			new Label(parent, SWT.NULL).setText("密码：");
			Text passwordText = new Text(parent, SWT.BORDER | SWT.WRAP);
			passwordText.setText(localConfig.getDbParam().getPassword());
			passwordText.setLayoutData(gd);
			passwordText.setEditable(false);

			new Label(parent, SWT.NULL).setText("包名：");
			Text packageNameText = new Text(parent, SWT.BORDER | SWT.WRAP);
			packageNameText.setText(localConfig.getDbParam().getPackageName());
			packageNameText.setLayoutData(gd);
			packageNameText.setEditable(false);

			new Label(parent, SWT.NULL).setText("源代码地址：");
			Text srcDirText = new Text(parent, SWT.BORDER | SWT.WRAP);
			srcDirText.setText(localConfig.getDbParam().getSrcDir());
			srcDirText.setLayoutData(gd);
			srcDirText.setEditable(false);

		}

	}

}
