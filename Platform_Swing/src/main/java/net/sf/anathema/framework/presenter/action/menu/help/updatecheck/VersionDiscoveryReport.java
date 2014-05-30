package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateState.CheckFailed;
import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateState.Checking;
import static net.sf.anathema.lib.message.MessageType.ERROR;
import static net.sf.anathema.lib.message.MessageType.INFORMATION;

public class VersionDiscoveryReport extends ProgressReportAdapter {
  private Resources resources;
  private final Version installed;
  private UpdateModel model;
  private final UpdateView view;

  public VersionDiscoveryReport(UpdateModel model, UpdateView view, Resources resources, Version installed) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.installed = installed;
    view.showLatestVersion("?.?.?");
  }

  @Override
  public void lookingUpLatestAvailableVersion() {
    view.showLatestVersion("?.?.?");
    view.showNoMessage();
    model.setState(Checking);
  }

  @Override
  public void latestAvailableVersionIs(Version available) {
    boolean isUpdateAvailable = updateAvailable(available);
    String messageKey = determineMessageToShow(isUpdateAvailable);
    view.showLatestVersion(available.asString());
    view.showMessage(resources.getString(messageKey), INFORMATION);
    model.setState(UpdateState.CheckSuccessful);
    if (isUpdateAvailable) {
      view.enableUpdate();
    }
    else {
      view.disableUpdate();
    }
    UpdateChangelog changelog = new UpdateChangelog(installed, available);
    view.showChangelog(changelog.getText());
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
    showErrorState("Help.UpdateCheck.GeneralException");
  }

  @Override
  public void versionLookupFailed(Exception e) {
    showErrorState("Help.UpdateCheck.IOException");
  }

  private void showErrorState(String key) {
    view.showLatestVersion("?.?.?");
    view.showMessage(resources.getString(key), ERROR);
    model.setState(CheckFailed);
  }

  private boolean updateAvailable(Version available) {
    return available.isGreaterThan(installed);
  }
}