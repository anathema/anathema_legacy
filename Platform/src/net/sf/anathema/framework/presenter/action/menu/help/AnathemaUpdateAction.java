package net.sf.anathema.framework.presenter.action.menu.help;

import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.disy.commons.swing.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import java.awt.Component;

public class AnathemaUpdateAction extends SmartAction {

  private static final long serialVersionUID = -9188349625150813107L;

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
      @Override
      public void changeOccurred() {
        dialog.updateDescription();
        dialog.getDialogControl().checkInputValid();
      }
    });
    dialog.show();
    new Thread(new Runnable() {
      @Override
      public void run() {
        updateChecker.checkForUpdates();
      }
    }).start();
  }
}