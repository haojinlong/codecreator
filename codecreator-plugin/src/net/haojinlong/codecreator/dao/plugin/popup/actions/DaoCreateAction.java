package net.haojinlong.codecreator.dao.plugin.popup.actions;

import net.haojinlong.codecreator.dao.plugin.wizard.CreateWizard;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class DaoCreateAction implements IObjectActionDelegate {

	private Shell shell;

	private IStructuredSelection selection;

	/**
	 * Constructor for Action1.
	 */
	public DaoCreateAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Object obj = null;
		IJavaProject project = null;

		if (selection != null) { // 判断selection是否为空，获取第一个选择项
			obj = selection.getFirstElement();
		} else {
			MessageDialog.openInformation(shell, "异常", "请选择Java项目");
			return;
		}
		if (obj instanceof IJavaProject) { // 判断选择的是否是Java工程
			project = (IJavaProject) obj;
			CreateWizard wizard = new CreateWizard(project);
			WizardDialog dialog = new WizardDialog(shell, wizard);
			dialog.setPageSize(300, 350);
			dialog.open();
		} else {
			MessageDialog.openInformation(shell, "异常", "请选择Java项目");
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection != null && selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
		}

	}

}
