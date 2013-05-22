package net.sf.anathema.character.perspective;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

public interface InteractionView {

  Tool addTool();

  ToggleTool addToggleTool();
}