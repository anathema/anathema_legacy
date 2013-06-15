package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;

public interface TraitTypeGroups {
  IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();

  IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();
}