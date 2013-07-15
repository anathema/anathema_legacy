package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface CharmSpecialsModel {
  int getCreationLearnCount();

  void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener);

  Charm getCharm();

  int getCurrentLearnCount();

  void forget();

  void learn(boolean experienced);
}