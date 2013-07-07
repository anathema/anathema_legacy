package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(ICharm[] learnedCharms);
}