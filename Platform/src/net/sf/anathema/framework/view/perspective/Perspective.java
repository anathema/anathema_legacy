package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.IResources;

public interface Perspective {

  void configureToggle(PerspectiveToggle toggle);

  void initContent(Container container, IApplicationModel applicationModel, IResources resources);
}
