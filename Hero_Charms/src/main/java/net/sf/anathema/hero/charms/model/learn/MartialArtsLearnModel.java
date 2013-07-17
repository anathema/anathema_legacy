package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface MartialArtsLearnModel {

  Charm[] getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}