package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IComboArbitrator extends IComboRules {

  public boolean isCharmComboLegal(ICharm charm);
}