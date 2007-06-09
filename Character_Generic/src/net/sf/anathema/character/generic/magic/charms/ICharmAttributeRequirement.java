package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmAttributeRequirement {

  public int getCount();

  public ICharmAttribute getAttribute();

  public String getStringRepresentation();

  boolean isFulfilled(ICharm[] charms);
}