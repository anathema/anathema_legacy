package net.sf.anathema.framework.view;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public interface Perspective {

  JComponent createContent(IAnathemaModel model, IResources resources, ReflectionsInstantiater instantiater);
}
