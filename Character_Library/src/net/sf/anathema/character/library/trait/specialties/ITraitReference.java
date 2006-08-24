package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.IResources;

public interface ITraitReference {

  public ITraitType getTraitType();

  public String createName(IResources resources);
}