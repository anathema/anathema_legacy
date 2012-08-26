package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;

public class VersionDiscoveryReport extends ProgressReportAdapter {
  private UpdateDialogPage page;
  private final Version installed;

  public VersionDiscoveryReport(UpdateDialogPage updateDialogPage, Version installed) {
    this.page = updateDialogPage;
    this.installed = installed;
  }

  @Override
  public void lookingUpLatestAvailableVersion() {
    page.clearState();
  }

  @Override
  public void latestAvailableVersionIs(Version available) {
    boolean isUpdateAvailable = updateAvailable(available);
    String messageKey = determineMessageToShow(isUpdateAvailable);
    page.setSuccessState(messageKey, available);
    if (isUpdateAvailable) {
      page.enableUpdate();
    }
    else {
      page.disableUpdate();
    }
    UpdateChangelog changelog = new UpdateChangelog(installed, available);
    page.showChangelog(changelog.getText());
  }

  private String determineMessageToShow(boolean available) {
    if (available) {
      return "Help.UpdateCheck.Outdated";
    } else {
      return "Help.UpdateCheck.UpToDate";
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

  private boolean updateAvailable(Version available) {
    return available.isGreaterThan(installed);
  }
}