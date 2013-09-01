package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.scribe.editor.presenter.ScrollPresenter;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.view.ScribeView;

public class ScribePresenter {

  private ScribeView view;
  private ScribeModel model;
  private Resources resources;

  public ScribePresenter(ScribeModel model, ScribeView view, Resources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  public void initPresentation() {
    new NavigationPresenter(model, view.scribeNavigation).initializeNavigationPresentation();
    initializeScrollPresentation();
    Tool tool = view.scribeNavigation.addTool();
    tool.setIcon(new BasicUi().getNewIconPathForTaskbar());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.scrollModel.startNewScroll();
      }
    });
  }

  private void initializeScrollPresentation() {
    ScrollPresenter scrollPresenter = new ScrollPresenter(model.scrollModel, view.scrollView.scrollEditor, view.scrollView.scrollPreview, resources);
    scrollPresenter.initPresentation();
  }

}
