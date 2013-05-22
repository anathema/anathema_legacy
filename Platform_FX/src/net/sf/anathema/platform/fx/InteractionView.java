package net.sf.anathema.platform.fx;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

public interface InteractionView {

  Tool addTool();

  ToggleTool addToggleTool();
}