/**
 * # TypeSelectPage.java -- (2014年11月4日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.dao.plugin.wizard;

import net.haojinlong.codecreator.commons.DaoType;
import net.haojinlong.codecreator.commons.DbType;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月4日
 *
 */
public class TypeSelectPage extends WizardPage {
	static Logger logger = LoggerFactory.getLogger(TypeSelectPage.class);

	// 数据库类型
	private DbType dbType;
	private Button mysqlRadio;
	private Button oracleRadio;
	private Button sybaseRadio;
	private Button sqlserverRadio;

	// Dao类型
	private DaoType daoType;
	private Button mybatisRadio;
	private Button hibernateRadio;
	private Button jpaRadio;

	public TypeSelectPage() {
		super("类型选择");
		setTitle("数据库类型和DAO类型选择");
		setDescription("目前仅支持mysql数据库的mybatis映射，其他数据库和其他OR映射框架的支持仍在建设中");
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
		Layout typeLayout = new GridLayout(1, false);
		GridData dbGridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData daoGridData = new GridData(GridData.FILL_HORIZONTAL);

		// 数据库类型区域
		Label dbTypeLabel = new Label(container, SWT.NULL);
		dbTypeLabel.setText("\n请选择数据库类型：");
		Composite dbTypeContainer = new Composite(container, SWT.BORDER);
		dbTypeContainer.setLayoutData(dbGridData);
		dbTypeContainer.setLayout(typeLayout);

		// DAO类型区域
		Label daoTypeLabel = new Label(container, SWT.NULL);
		daoTypeLabel.setText("\n请选择OR映射框架：");
		Composite daoTypeContainer = new Composite(container, SWT.BORDER);
		daoTypeContainer.setLayoutData(daoGridData);
		daoTypeContainer.setLayout(typeLayout);

		// mysql
		mysqlRadio = new Button(dbTypeContainer, SWT.RADIO);
		mysqlRadio.setText("MySQL数据库");
		mysqlRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbType = DbType.mysql;
			}
		});

		// oracle
		oracleRadio = new Button(dbTypeContainer, SWT.RADIO);
		oracleRadio.setText("Oracle数据库[暂不支持]");
		oracleRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbType = DbType.oracle;
			}
		});

		// sybase
		sybaseRadio = new Button(dbTypeContainer, SWT.RADIO);
		sybaseRadio.setText("Sybase数据库[暂不支持]");
		sybaseRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbType = DbType.sybase;
			}
		});

		// sqlserver
		sqlserverRadio = new Button(dbTypeContainer, SWT.RADIO);
		sqlserverRadio.setText("SqlServer数据库[暂不支持]");
		sqlserverRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbType = DbType.sqlserver;
			}
		});

		// mybatis
		mybatisRadio = new Button(daoTypeContainer, SWT.RADIO);
		mybatisRadio.setText("使用mybatis OR映射");
		mybatisRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				daoType = DaoType.mybatis;
			}
		});

		// hibernate
		hibernateRadio = new Button(daoTypeContainer, SWT.RADIO);
		hibernateRadio.setText("使用hibernate OR映射[暂不支持]");
		hibernateRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				daoType = DaoType.hibernate;
			}
		});

		// jpa
		jpaRadio = new Button(daoTypeContainer, SWT.RADIO);
		jpaRadio.setText("使用jpa OR映射[暂不支持]");
		jpaRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				daoType = DaoType.jpa;
			}
		});
		init();
		setControl(container);
	}

	// 初始化，设置默认数据库和DAO类型，暂不支持的数据库和DAO不可用
	private void init() {
		mysqlRadio.setSelection(true);
		dbType = DbType.mysql;
		mybatisRadio.setSelection(true);
		daoType = DaoType.mybatis;

		oracleRadio.setEnabled(false);
		sybaseRadio.setEnabled(false);
		sqlserverRadio.setEnabled(false);
		hibernateRadio.setEnabled(false);
		jpaRadio.setEnabled(false);
	}

	/**
	 * @return the dbType
	 */
	public DbType getDbType() {
		return dbType;
	}

	/**
	 * @return the daoType
	 */
	public DaoType getDaoType() {
		return daoType;
	}

}
