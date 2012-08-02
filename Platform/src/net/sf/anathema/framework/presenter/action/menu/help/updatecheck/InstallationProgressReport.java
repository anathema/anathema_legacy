package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;

public class InstallationProgressReport extends ProgressReportAdapter {
  private final UpdateDialogPage page;
  private int elementsHandled = 0;

  public InstallationProgressReport(UpdateDialogPage page) {
    this.page = page;
  }

  @Override
  public void startingInstallationOf(Version version) {
    elementsHandled = 0;
    page.showInstallationRunning();
  }

  @Override
  public void foundElementsToInstall(int numberOfElements) {
    page.showFilesToDownload(numberOfElements);
  }

  @Override
  public void finishedFile() {
    elementsHandled++;
    page.showFilesAlreadyLoaded(elementsHandled);
  }

  @Override
  public void finishedInstallation() {
    page.showInstallationDone();
  }
}