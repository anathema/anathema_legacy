package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface IGenericCombo {

  String getName();

  Charm[] getCharms();
}