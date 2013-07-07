package net.sf.anathema.character.main.magic.charms.combo;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICombo;

public interface IComboArbitrator extends IComboRules {

  boolean canBeAddedToCombo(ICombo combo, ICharm charm);
}