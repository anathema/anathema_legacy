package net.sf.anathema.character.main.magic.model.charm;

public interface IndirectCharmRequirement {

  String getStringRepresentation();

  boolean isFulfilled(Charm[] learnedCharms);
}