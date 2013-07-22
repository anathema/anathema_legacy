package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface CharmSpecialsModel {
  int getCreationLearnCount();

  void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener);

  Charm getCharm();

  int getCurrentLearnCount();

  void forget();

  void learn(boolean experienced);
}