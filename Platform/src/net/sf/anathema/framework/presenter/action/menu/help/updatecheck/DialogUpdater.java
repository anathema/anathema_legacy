package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;

public class DialogUpdater extends ProgressReportAdapter {
  private final UserDialog dialog;

  public DialogUpdater(UserDialog dialog) {
    this.dialog = dialog;
  }

  @Override
  public void lookingUpLatestAvailableVersion() {
    updateDialog();
  }

  @Override
  public void latestAvailableVersionIs(Version value) {
    updateDialog();
  }

  @Override
  public void versionLookupFailed() {
    updateDialog();
  }

  @Override
  public void versionLookupFailed(Exception e) {
    updateDialog();
  }

  @Override
  public void startingInstallationOf(Version version) {
    updateDialog();
  }

  @Override
  public void finishedInstallation() {
    updateDialog();
  }

  private void updateDialog() {
    dialog.updateDescription();
    dialog.getDialogControl().checkInputValid();
  }
}