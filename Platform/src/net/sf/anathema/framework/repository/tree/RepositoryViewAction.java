package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import java.awt.Component;

public final class RepositoryViewAction extends SmartAction {
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
    DefaultDialogConfiguration dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog userDialog = new UserDialog(parentComponent, dialogConfiguration);
    userDialog.getDialog().setModal(true);
    userDialog.getDialog().setResizable(false);
    userDialog.show();
  }
}