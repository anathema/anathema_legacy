package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;

public class UiProgressReport extends ProgressReportAdapter {
  private UpdateDialogPage page;

  public UiProgressReport(UpdateDialogPage updateDialogPage) {
    this.page = updateDialogPage;
  }

  @Override
  public void lookingUpLatestAvailableVersion() {
    page.clearState();
  }

  @Override
  public void latestAvailableVersionIs(Version value) {
    page.setSuccessState("Help.UpdateCheck.Outdated", value);
  }

  @Override
  public void versionLookupFailed() {
    page.setErrorState("Help.UpdateCheck.GeneralException");
  }

  @Override
  public void versionLookupFailed(Exception e) {
    page.setErrorState("Help.UpdateCheck.IOException");
  }
}