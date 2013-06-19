package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.traits.TraitType;

public interface IIdentifiedCasteTraitTypeGroup extends IIdentifiedTraitTypeGroup {

  CasteType[] getTraitCasteTypes(TraitType type);
}