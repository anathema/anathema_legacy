package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.model.HeroModel;

public interface TraitListModel extends TraitMap, HeroModel {

  IdentifiedTraitTypeList[] getTraitTypeList();

  int getTraitMaximum();
}
