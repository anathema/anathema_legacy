package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;

public class UpdateAction implements Command {

  private Resources resources;

  public UpdateAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void execute() {
    String title = resources.getString("Help.UpdateCheck.Title");
    String currentVersionLabel = resources.getString("Help.UpdateCheck.CurrentVersion") + ":";
    String latestVersionLabel = resources.getString("Help.UpdateCheck.LatestVersion") + ":";
    UpdateView view = new SwingUpdateView(title, currentVersionLabel, latestVersionLabel);
    UpdateModel model = new UpdateModel();
    UpdatePresenter updatePresenter = new UpdatePresenter(resources, view, model);
    updatePresenter.initPresentation();
  }
}