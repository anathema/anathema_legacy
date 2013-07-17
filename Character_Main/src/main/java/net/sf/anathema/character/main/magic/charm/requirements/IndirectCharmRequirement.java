package net.sf.anathema.character.main.magic.charm.requirements;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(Charm[] learnedCharms);
}