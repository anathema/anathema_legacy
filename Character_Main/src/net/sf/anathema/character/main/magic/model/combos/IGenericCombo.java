package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IGenericCombo {

  String getName();

  ICharm[] getCharms();
}