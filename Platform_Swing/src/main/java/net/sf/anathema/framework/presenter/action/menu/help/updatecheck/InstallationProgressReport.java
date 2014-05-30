package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.framework.environment.Resources;

public class InstallationProgressReport extends ProgressReportAdapter {
  private final Resources resources;
  private final UpdateView view;
  private final UpdateModel model;
  private int elementsHandled = 0;
  private long bytesHandled = 0;
  private long expectedSize;

  public InstallationProgressReport(Resources resources, UpdateView view, UpdateModel model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  @Override
  public void startingInstallationOf(Version version) {
    elementsHandled = 0;
    model.setState(UpdateState.InstallationRunning);
  }

  @Override
  public void foundElementsToInstall(int numberOfElements) {
    view.showFilesToDownload(numberOfElements);
  }

  @Override
  public void expectedSize(long size) {
    this.expectedSize = size;
    int intSize = toInteger(size);
    view.showExpectedFileSize(intSize);
    bytesHandled = 0;
    progress(0);
  }

  @Override
  public void progress(long progress) {
    bytesHandled += progress;
    view.showProgressOnFile(toInteger(bytesHandled));
  }

  @Override
  public void finishedFile() {
    elementsHandled++;
    view.showFilesAlreadyLoaded(elementsHandled);
    view.showProgressOnFile(toInteger(expectedSize));
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
    view.showDescription(resources.getString("Help.UpdateInstallation.RestartRequired"));
    model.setState(UpdateState.InstallationDone);
    view.showProgressMessage(resources.getString("Help.UpdateInstallation.Done"));
  }
}