package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmAttributeRequirement {

  String getStringRepresentation();

  boolean isFulfilled(ICharm[] charms);

  IIdentificate getAttribute();

  int getCount();

}