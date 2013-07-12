package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.interaction.Tool;

public interface ComboView {

  void initGui(String name, String description);

  void updateCombo(String name, String description);

  Tool addTool();

  void remove();
}