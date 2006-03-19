package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.DefaultUserDialog;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.framework.AboutDialogPage;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaAboutAction extends SmartAction {

  private final IResources resources;

  public AnathemaAboutAction(IResources resources) {
    this.resources = resources;
    setName(resources.getString("Help.AboutDialog.Title")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    AboutDialogPage page = new AboutDialogPage(resources);
    DefaultUserDialog userDialog = new DefaultUserDialog(page, DialogButtonConfiguration.createOnlyOkay());
    UserDialog dialog = new UserDialog(parentComponent, userDialog);
    dialog.show();
  }
}