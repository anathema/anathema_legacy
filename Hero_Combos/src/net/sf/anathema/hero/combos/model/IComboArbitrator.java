package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.combos.display.presenter.ICombo;

public interface IComboArbitrator extends IComboRules {

  boolean canBeAddedToCombo(ICombo combo, Charm charm);
}