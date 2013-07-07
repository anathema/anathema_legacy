package net.sf.anathema.character.main.magic.display.view.combos;

import net.sf.anathema.interaction.Tool;

public interface IComboView {

  void initGui(String name, String description);

  void updateCombo(String name, String description);

  Tool addTool();
}