package net.sf.anathema.interaction;

import net.sf.anathema.framework.perspective.ToolBar;

public interface Interaction extends Tool {

  void addTo(ToolBar toolbar);
}
