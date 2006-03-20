package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IComboRules {

  public boolean isComboLegal(ICharm charm1, ICharm charm2);
}