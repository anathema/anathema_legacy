package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface IComboRules {

  boolean isComboLegal(Charm charm1, Charm charm2);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}