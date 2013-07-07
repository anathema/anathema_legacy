package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(ICharm[] learnedCharms);
}