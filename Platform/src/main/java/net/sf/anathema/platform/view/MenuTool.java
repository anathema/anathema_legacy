package net.sf.anathema.platform.view;

import net.sf.anathema.interaction.Tool;

public interface MenuTool extends Tool {

  void clearMenu();

  Tool addMenuEntry();
}