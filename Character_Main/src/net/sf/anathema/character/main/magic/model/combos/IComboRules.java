package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IComboRules {

  boolean isComboLegal(ICharm charm1, ICharm charm2);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}