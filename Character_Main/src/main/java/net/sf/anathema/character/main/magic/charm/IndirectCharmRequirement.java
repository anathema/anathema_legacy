package net.sf.anathema.character.main.magic.charm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(Charm[] learnedCharms);
}