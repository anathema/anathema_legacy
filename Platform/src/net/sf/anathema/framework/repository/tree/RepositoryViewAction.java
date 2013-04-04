package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import java.awt.Component;

public final class RepositoryViewAction extends SmartAction {
  private final IApplicationModel model;
  private final Resources resources;

  public static Action createMenuAction(Resources resources, IApplicationModel anathemaModel) {
    SmartAction action = new RepositoryViewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.RepositoryView.ActionTitle") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  private RepositoryViewAction(IApplicationModel model, Resources resources) {
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