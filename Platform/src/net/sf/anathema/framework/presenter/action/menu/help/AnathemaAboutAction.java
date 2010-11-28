package net.sf.anathema.framework.presenter.action.menu.help;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.DefaultUserDialogConfiguration;
import net.disy.commons.swing.dialog.userdialog.IDialogPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaAboutAction extends SmartAction {

  private final IResources resources;

  public static Action createMenuAction(IResources resources) {
    SmartAction action = new AnathemaAboutAction(resources);
    action.setName(resources.getString("Help.AboutDialog.Title")); //$NON-NLS-1$
    return action;
  }

  private AnathemaAboutAction(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    IDialogPage page = new AboutDialogPage(resources);
    UserDialog dialog = new UserDialog(parentComponent, new DefaultUserDialogConfiguration(
        page,
        DialogButtonConfigurationFactory.createOnlyOkay()));
    dialog.show();
  }
}