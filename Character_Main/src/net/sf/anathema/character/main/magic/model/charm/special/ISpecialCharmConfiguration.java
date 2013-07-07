package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface ISpecialCharmConfiguration {
  int getCreationLearnCount();

  void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener);

  ICharm getCharm();

  int getCurrentLearnCount();

  void forget();

  void learn(boolean experienced);
}