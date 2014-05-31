package net.sf.anathema.scribe.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.presenter.ScribePresenter;
import net.sf.anathema.scribe.perspective.view.ScribeView;

@PerspectiveAutoCollector
@Weight(weight = 200)
public class ScribePerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/Scroll20.png"));
    toggle.setTooltip("Scribe.Perspective.Name");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    ScribeView view = new ScribeView();
    ScribeModel scribeModel = new ScribeModel(applicationModel);
    new ScribePresenter(scribeModel, view, environment).initPresentation();
    container.setContent(view.perspectivePane.getNode());
  }
}
