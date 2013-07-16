package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface ComboRules {

  boolean isComboLegal(Charm charm1, Charm charm2);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}