package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.lib.resources.IResources;

public interface Perspective {

  void configureToggle(PerspectiveToggle toggle);

  void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory);
}
