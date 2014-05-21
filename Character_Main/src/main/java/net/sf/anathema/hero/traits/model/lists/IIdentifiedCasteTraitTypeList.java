package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.concept.CasteType;

public interface IIdentifiedCasteTraitTypeList extends IdentifiedTraitTypeList {

  CasteType[] getTraitCasteTypes(TraitType type);
}