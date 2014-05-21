package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.traits.TraitMap;

public interface TraitListModel extends TraitMap, HeroModel {

  IdentifiedTraitTypeList[] getTraitTypeList();

  int getTraitMaximum();
}
