package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;

public interface MartialArtsCharmConfiguration {
  ICharm[] getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}