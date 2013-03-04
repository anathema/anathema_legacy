package net.sf.anathema.scribe.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.presenter.ScribePresenter;
import net.sf.anathema.scribe.perspective.view.ScribeView;

@PerspectiveAutoCollector
@Weight(weight = 200)
public class ScribePerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("Kompass24.png");
    toggle.setTooltip("Scribe.Perspective.Name");
  }

  @Override
  public void initContent(Container container, final IAnathemaModel model, IResources resources) {
    ScribeView view = new ScribeView();
    ScribeModel scribeModel = new ScribeModel(model);
    new ScribePresenter(view, scribeModel).initPresentation();
    container.setSwingContent(view.perspectivePane.getComponent());
  }
}
