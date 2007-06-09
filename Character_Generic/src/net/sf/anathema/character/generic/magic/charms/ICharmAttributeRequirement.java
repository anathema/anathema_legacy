package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmAttributeRequirement {

  public String getStringRepresentation();

  boolean isFulfilled(ICharm[] charms);
}