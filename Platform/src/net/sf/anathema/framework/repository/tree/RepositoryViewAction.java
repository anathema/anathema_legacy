package net.sf.anathema.framework.repository.tree;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.DefaultDialogConfigurationBuilder;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.disy.commons.swing.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public final class RepositoryViewAction extends SmartAction {
  private static final long serialVersionUID = 8273752454261073572L;
  private final IAnathemaModel model;
  private final IResources resources;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new RepositoryViewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.RepositoryView.ActionTitle") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private RepositoryViewAction(IAnathemaModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    RepositoryBrowserDialogPage page = new RepositoryBrowserDialogPage(resources, model);
    DefaultDialogConfigurationBuilder<IDialogPage> dialogBuilder = new DefaultDialogConfigurationBuilder<IDialogPage>(page);
    dialogBuilder.setButtonConfiguration(DialogButtonConfigurationFactory.createOkOnly());
    UserDialog userDialog = new UserDialog(parentComponent, dialogBuilder.build());
    userDialog.getDialog().setModal(true);
    userDialog.getDialog().setResizable(false);
    userDialog.show();
  }
}