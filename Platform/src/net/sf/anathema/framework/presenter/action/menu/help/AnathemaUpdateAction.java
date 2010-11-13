package net.sf.anathema.framework.presenter.action.menu.help;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.IDialogPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaUpdateAction extends SmartAction {

  public static Action createMenuAction(IResources resources) {
    AnathemaUpdateAction action = new AnathemaUpdateAction(resources);
    action.setName(resources.getString("Help.UpdateCheck.Title")); //$NON-NLS-1$
    return action;
  }

  private IResources resources;

  public AnathemaUpdateAction(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    final UpdateChecker updateChecker = new UpdateChecker(resources);
    IDialogPage page = new UpdateDialogPage(resources, updateChecker);
    final UserDialog dialog = new UserDialog(parentComponent, page);
    dialog.getDialog().setModal(false);
    updateChecker.addDataChangedListener(new IChangeListener() {
      public void changeOccured() {
        dialog.updateDescription();
        dialog.getDialogControl().checkInputValid();
      }
    });
    dialog.show();
    new Thread(new Runnable() {
      public void run() {
        updateChecker.checkForUpdates();
      }
    }).start();
  }
}