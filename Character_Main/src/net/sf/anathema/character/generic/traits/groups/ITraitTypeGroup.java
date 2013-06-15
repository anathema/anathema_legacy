package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTypeGroup {

  ITraitType getById(String typeId);

  ITraitType[] getAllGroupTypes();

  boolean contains(ITraitType traitType);
}