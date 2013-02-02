package net.sf.anathema.integrated;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.Perspective;
import net.sf.anathema.framework.view.PerspectiveAutoCollector;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

@PerspectiveAutoCollector
public class IntegratedPerspective implements Perspective {

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionsInstantiater instantiater) {
    IntegratedPerspectiveView view = new IntegratedPerspectiveView();
    new IntegratedPerspectivePresenter(model, view, resources,  instantiater).initPresentation();
    return view.createContent();
  }
}
