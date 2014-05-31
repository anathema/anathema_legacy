package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.UiEnvironment;

public interface Perspective {

  void configureToggle(PerspectiveToggle toggle);

  void initContent(Container container, IApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment);
}
