package net.sf.anathema.hero.combos.display;

import net.sf.anathema.interaction.Tool;

public interface ComboView {

  void initGui(String name, String description);

  void updateCombo(String name, String description);

  Tool addTool();
}