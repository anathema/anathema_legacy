package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.combos.display.presenter.Combo;

public interface ComboArbitrator extends ComboRules {

  boolean canBeAddedToCombo(Combo combo, Charm charm);
}