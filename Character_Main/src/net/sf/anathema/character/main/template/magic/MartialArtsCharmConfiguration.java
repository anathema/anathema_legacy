package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface MartialArtsCharmConfiguration {
  Charm[] getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}