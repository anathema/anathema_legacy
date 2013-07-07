package net.sf.anathema.character.main.charm.combo;

import net.sf.anathema.character.main.magic.ICharm;

public interface IComboRules {

  boolean isComboLegal(ICharm charm1, ICharm charm2);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}