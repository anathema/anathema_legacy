package net.sf.anathema.character.model.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.library.trait.ITrait;

public interface IMultiLearnableCharmConfiguration extends ISpecialCharmConfiguration {

  public ITrait getCategory();

  public void setCurrentLearnCount(int newValue);
}