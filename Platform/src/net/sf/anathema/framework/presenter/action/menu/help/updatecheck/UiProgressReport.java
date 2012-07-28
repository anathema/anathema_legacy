package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;

public class UiProgressReport extends ProgressReportAdapter {
  private UpdateDialogPage page;
  private final Version installed;

  public UiProgressReport(UpdateDialogPage updateDialogPage, Version installed) {
    this.page = updateDialogPage;
    this.installed = installed;
  }

  @Override
  public void lookingUpLatestAvailableVersion() {
    page.clearState();
  }

  @Override
  public void latestAvailableVersionIs(Version available) {
    if (available.isGreaterThan(installed)) {
      page.setSuccessState("Help.UpdateCheck.Outdated", available);
    }
    else{
      page.setSuccessState("Help.UpdateCheck.UpToDate", available);
    }
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