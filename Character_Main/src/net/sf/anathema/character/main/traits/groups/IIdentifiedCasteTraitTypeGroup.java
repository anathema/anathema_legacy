package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.concept.CasteType;

public interface IIdentifiedCasteTraitTypeGroup extends IIdentifiedTraitTypeGroup {

  CasteType[] getTraitCasteTypes(TraitType type);
}