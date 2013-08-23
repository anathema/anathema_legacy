package net.sf.anathema.platform.fx;

import net.sf.anathema.interaction.Tool;

public interface MenuTool extends Tool {

  void clearMenu();

  Tool addMenuEntry();
}