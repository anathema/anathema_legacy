package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ISpecialCharmConfiguration {
  public int getCreationLearnCount();

  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener);

  public ICharm getCharm();

  public int getCurrentLearnCount();

  public void forget();
}