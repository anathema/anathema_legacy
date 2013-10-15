package net.sf.anathema.platform.view;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

public interface InteractionView {

  Tool addTool();

  ToggleTool addToggleTool();

  MenuTool addMenuTool();
}