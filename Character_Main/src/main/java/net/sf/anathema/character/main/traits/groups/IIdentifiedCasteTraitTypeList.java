package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.concept.CasteType;

public interface IIdentifiedCasteTraitTypeList extends IdentifiedTraitTypeList {

  CasteType[] getTraitCasteTypes(TraitType type);
}