package net.sf.anathema.demo.platform.dialog;

import de.jdemo.extensions.SwingDemoCase;
import net.disy.commons.swing.dialog.userdialog.IDialogPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.presenter.action.menu.help.UpdateChecker;
import net.sf.anathema.framework.presenter.action.menu.help.UpdateDialogPage;
import net.sf.anathema.framework.resources.AnathemaResources;

public class UpdateDialogDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    UpdateChecker updateChecker = new UpdateChecker(resources);
    updateChecker.checkForUpdates();
    IDialogPage page = new UpdateDialogPage(resources, updateChecker);
    UserDialog dialog = new UserDialog(null, page);
    show(dialog.getDialog().getWindow());
  }
}
