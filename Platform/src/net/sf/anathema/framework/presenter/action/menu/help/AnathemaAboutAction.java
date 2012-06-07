package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import java.awt.Component;

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
    DefaultDialogConfiguration dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog dialog = new UserDialog(parentComponent, dialogConfiguration);
    dialog.show();
  }
}