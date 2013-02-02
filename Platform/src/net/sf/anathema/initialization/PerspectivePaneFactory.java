package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.Perspective;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.integrated.IntegratedPerspective;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public class PerspectivePaneFactory implements ViewFactory {

  private IAnathemaModel model;
  private IResources resources;
  private ReflectionsInstantiater instantiater;

  public PerspectivePaneFactory(IAnathemaModel model, IResources resources, ReflectionsInstantiater instantiater) {
    this.model = model;
    this.resources = resources;
    this.instantiater = instantiater;
  }

  @Override
  public JComponent createContent() {
    Perspective perspective = new IntegratedPerspective();
    return perspective.createContent(model, resources, instantiater);
  }
}
