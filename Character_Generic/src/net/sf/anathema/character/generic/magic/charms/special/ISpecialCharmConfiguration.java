package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ISpecialCharmConfiguration {
  int getCreationLearnCount();

  void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener);

  ICharm getCharm();

  int getCurrentLearnCount();

  void forget();

  void learn(boolean experienced);
}