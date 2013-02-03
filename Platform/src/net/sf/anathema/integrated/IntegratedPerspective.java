package net.sf.anathema.integrated;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveContainer;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

@PerspectiveAutoCollector
@Weight(weight = 9999)
public class IntegratedPerspective implements Perspective {

  @Override
  public String getTitle() {
    return "Integrated";
  }

  @Override
  public void initContent(PerspectiveContainer container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    IntegratedPerspectiveView view = new IntegratedPerspectiveView();
    new IntegratedPerspectivePresenter(model, view, resources,  objectFactory).initPresentation();
    container.setSwingContent(view.createContent());
  }
}
