/**
 * # DestSelectPage.java -- (2014年11月4日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月4日
 *
 */
public class DestSelectPage extends WizardPage {
	static Logger logger = LoggerFactory.getLogger(DestSelectPage.class);

	private IJavaProject project;
	private String destDir;
	private Text destDirText;
	private Button basicRadio;
	private Button mavenRadio;
	private String basicDir;
	private String mavenDir;
	private String packageName;
	Text packageNameText;

	ContainerSelectionDialog dialog;

	public DestSelectPage(IJavaProject project) {
		super("destSelect");
		this.project = project;
		setTitle("选择源代码生成目录");
		setDescription("请选择源代码生成目录，若为普通工程请选择“src”，若为maven项目，请选择“src/main/java”");
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
		// 总容器
		Composite container = new Composite(parent, SWT.NULL);
		Layout layout = new GridLayout(1, false);
		container.setLayout(layout);

		// 文件选择容器
		Composite destContainer = new Composite(container, SWT.BORDER);
		Layout destLayout = new GridLayout(3, false);
		destContainer.setLayout(destLayout);
		GridData destGridData = new GridData(GridData.FILL_HORIZONTAL);
		destContainer.setLayoutData(destGridData);

		// 中间行布局
		GridData textGridData = new GridData(GridData.FILL_HORIZONTAL);

		// 第一行：地址框和选择按钮
		Label dirLabel = new Label(destContainer, SWT.NULL);
		dirLabel.setText("目标地址：");
		destDirText = new Text(destContainer, SWT.BORDER | SWT.SINGLE);
		destDirText.setLayoutData(textGridData);
		destDirText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
			}
		});
		Button destButton = new Button(destContainer, SWT.PUSH);
		destButton.setText("选择");
		destButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				browse();
			}
		});

		// 第2行，选择src或者src/main/java
		new Label(destContainer, SWT.NULL).setText("传统项目：");
		;
		basicRadio = new Button(destContainer, SWT.RADIO);
		basicRadio.setLayoutData(textGridData);
		basicRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				destDir = basicDir;
				destDirText.setText(basicDir);
			}
		});
		new Label(destContainer, SWT.NULL);

		// 第3行，选择maven项目
		new Label(destContainer, SWT.NULL).setText("maven项目");
		mavenRadio = new Button(destContainer, SWT.RADIO);
		mavenRadio.setLayoutData(textGridData);
		mavenRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				destDir = mavenDir;
				destDirText.setText(mavenDir);
			}
		});
		new Label(destContainer, SWT.NULL);

		// 第四行
		new Label(destContainer, SWT.NULL).setText("基础包名：");
		packageNameText = new Text(destContainer, SWT.BORDER | SWT.SINGLE);
		packageNameText.setLayoutData(textGridData);
		packageNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				packageName = packageNameText.getText();
				if (packageName == null || packageName.length() == 0) {
					updateStatus("请输入基础包名");
					return;
				}
				updateStatus(null);
			}
		});
		new Label(destContainer, SWT.NULL);

		// 初始化dialog
		dialog = new ContainerSelectionDialog(getShell(), project.getProject(),
				false, "请选择目录");

		init();
		updateStatus("请输入基础包名");
		setControl(container);
	}

	private void updateStatus(String msg) {
		setErrorMessage(msg);
		setPageComplete(msg == null);
	}

	// 初始化
	private void init() {
		basicDir = project.getProject().getFullPath().toString() + "/" + "src";
		mavenDir = project.getProject().getFullPath().toString() + "/"
				+ "src/main/java";
		basicRadio.setText(basicDir);
		mavenRadio.setText(mavenDir);
		destDir = basicDir;
		destDirText.setText(basicDir);
		destDirText.setEnabled(false);
		if (project.getProject().getFolder("/src/main/java").exists()) {
			mavenRadio.setSelection(true);
			destDirText.setText(mavenDir);
			destDir = mavenDir;
		} else {
			basicRadio.setSelection(true);
			destDirText.setText(basicDir);
			destDir = basicDir;
		}
	}

	// 打卡目录选择框
	private void browse() {
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				destDirText.setText(((Path) result[0]).toString());
				destDir = destDirText.getText();
				basicRadio.setSelection(false);
				mavenRadio.setSelection(false);
				if (destDir.equals(basicDir)) {
					basicRadio.setSelection(true);
				}
				if (destDir.equals(mavenDir)) {
					mavenRadio.setSelection(true);
				}
			}
		}
	}

	/**
	 * @return the destDir
	 */
	public String getDestDir() {
		return destDir;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

}
