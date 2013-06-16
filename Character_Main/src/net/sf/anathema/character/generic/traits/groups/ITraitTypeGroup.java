package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.TraitType;

public interface ITraitTypeGroup {

  TraitType getById(String typeId);

  TraitType[] getAllGroupTypes();

  boolean contains(TraitType traitType);
}