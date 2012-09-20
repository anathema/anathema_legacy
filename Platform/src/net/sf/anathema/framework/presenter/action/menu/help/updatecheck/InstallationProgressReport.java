package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;

public class InstallationProgressReport extends ProgressReportAdapter {
  private final UpdateDialogPage page;
  private int elementsHandled = 0;
  private long bytesHandled = 0;
  private long expectedSize;

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
  public void expectedSize(long size) {
    this.expectedSize = size;
    int intSize = toInteger(size);
    page.showExpectedFileSize(intSize);
    bytesHandled = 0;
    progress(0);
  }

  @Override
  public void progress(long progress) {
    bytesHandled += progress;
    page.showProgressOnFile(toInteger(bytesHandled));
  }

  @Override
  public void finishedFile() {
    elementsHandled++;
    page.showFilesAlreadyLoaded(elementsHandled);
    page.showProgressOnFile(toInteger(expectedSize));
  }

  private int toInteger(long longValue) {
    int intSize;
    if (longValue > Integer.MAX_VALUE) {
      intSize = Integer.MAX_VALUE;
    } else {
      intSize = (int) longValue;
    }
    return intSize;
  }

  @Override
  public void finishedInstallation() {
    page.showInstallationDone();
  }
}