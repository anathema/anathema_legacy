package net.sf.anathema.character.view.magic;

import net.sf.anathema.interaction.Tool;

public interface IComboView {

  void initGui(String name, String description);

  void updateCombo(String name, String description);

  Tool addTool();
}