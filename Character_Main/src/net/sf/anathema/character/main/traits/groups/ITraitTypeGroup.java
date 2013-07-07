package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitTypeGroup {

  TraitType getById(String typeId);

  TraitType[] getAllGroupTypes();

  boolean contains(TraitType traitType);
}