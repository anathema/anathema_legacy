package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface MartialArtsCharmConfiguration {
  ICharm[] getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}