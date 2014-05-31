package net.sf.anathema.framework.presenter.action.updatecheck;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.interaction.Command;

public class UpdateAction implements Command {

  private Resources resources;
  private UiEnvironment uiEnvironment;

  public UpdateAction(Resources resources, UiEnvironment uiEnvironment) {
    this.resources = resources;
    this.uiEnvironment = uiEnvironment;
  }

  @Override
  public void execute() {
    String title = resources.getString("Help.UpdateCheck.Title");
    String currentVersionLabel = resources.getString("Help.UpdateCheck.CurrentVersion") + ":";
    String latestVersionLabel = resources.getString("Help.UpdateCheck.LatestVersion") + ":";
    UpdateView view = new FxUpdateView(title, currentVersionLabel, latestVersionLabel, uiEnvironment);
    UpdateModel model = new UpdateModel();
    UpdatePresenter updatePresenter = new UpdatePresenter(resources, view, model);
    updatePresenter.initPresentation();
  }
}