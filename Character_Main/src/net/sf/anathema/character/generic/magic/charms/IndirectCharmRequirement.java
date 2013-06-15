package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(ICharm[] learnedCharms);
}