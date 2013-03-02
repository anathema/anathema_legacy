package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IComboRules {

  boolean isComboLegal(ICharm charm1, ICharm charm2);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}