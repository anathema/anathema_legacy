package net.sf.anathema.framework.presenter.action.updatecheck;

import de.idos.updates.Version;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateChangelog;
import net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateState;

import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateState.CheckFailed;
import static net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateState.Checking;

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
    model.setState(Checking);
  }

  @Override
  public void latestAvailableVersionIs(Version available) {
    boolean isUpdateAvailable = updateAvailable(available);
    String messageKey = determineMessageToShow(isUpdateAvailable);
    view.showLatestVersion(available.asString());
    view.showDescription(resources.getString(messageKey));
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
    model.setState(CheckFailed);
  }

  private boolean updateAvailable(Version available) {
    return available.isGreaterThan(installed);
  }
}