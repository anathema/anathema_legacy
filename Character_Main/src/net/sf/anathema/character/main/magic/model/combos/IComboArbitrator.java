package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface IComboArbitrator extends IComboRules {

  boolean canBeAddedToCombo(ICombo combo, Charm charm);
}