package net.sf.anathema.framework.presenter.action.menu.help;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.DefaultDialogConfigurationBuilder;
import net.disy.commons.swing.dialog.userdialog.page.IDialogPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaAboutAction extends SmartAction {

  private static final long serialVersionUID = 7136443647218989729L;
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
    DefaultDialogConfigurationBuilder<IDialogPage> dialogBuilder = new DefaultDialogConfigurationBuilder<IDialogPage>(page);
    dialogBuilder.setButtonConfiguration(DialogButtonConfigurationFactory.createOkOnly());
    UserDialog dialog = new UserDialog(parentComponent, dialogBuilder.build());
    dialog.show();
  }
}