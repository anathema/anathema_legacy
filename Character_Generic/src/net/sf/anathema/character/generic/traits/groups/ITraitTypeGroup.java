package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTypeGroup {

  public ITraitType getById(String typeId);

  public ITraitType[] getAllGroupTypes();

  public boolean contains(ITraitType traitType);
}