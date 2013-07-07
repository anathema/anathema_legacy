package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IComboArbitrator extends IComboRules {

  boolean canBeAddedToCombo(ICombo combo, ICharm charm);
}