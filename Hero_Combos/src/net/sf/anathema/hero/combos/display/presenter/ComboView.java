package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.interaction.Tool;

public interface ComboView {

  void displayComboName(String name);

  Tool addTool();

  void remove();

  void displayCharmNames(String commaSeparatedCharms);

  void displayDescription(String text);
}