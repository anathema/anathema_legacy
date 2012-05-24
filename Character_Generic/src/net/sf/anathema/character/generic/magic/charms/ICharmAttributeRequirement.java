package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.util.Identified;

public interface ICharmAttributeRequirement {

  String getStringRepresentation();

  boolean isFulfilled(ICharm[] charms);

  Identified getAttribute();

  int getCount();

}