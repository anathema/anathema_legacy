package net.sf.anathema.integrated;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@PerspectiveAutoCollector
@Weight(weight = 9999)
public class IntegratedPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setText("Integrated");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources) {
    IntegratedPerspectiveView view = new IntegratedPerspectiveView();
    new IntegratedPerspectivePresenter(model, view, resources).initPresentation();
    container.setSwingContent(view.createContent());
  }
}
