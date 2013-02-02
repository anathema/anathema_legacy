package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public interface Perspective {

  String getTitle();

  JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory instantiater);
}
