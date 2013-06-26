package net.sf.anathema.character.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICombo;

public interface IComboArbitrator extends IComboRules {

  boolean canBeAddedToCombo(ICombo combo, ICharm charm);
}