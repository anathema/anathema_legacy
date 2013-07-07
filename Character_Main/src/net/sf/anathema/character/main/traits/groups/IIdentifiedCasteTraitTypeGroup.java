package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.traits.TraitType;

public interface IIdentifiedCasteTraitTypeGroup extends IIdentifiedTraitTypeGroup {

  CasteType[] getTraitCasteTypes(TraitType type);
}