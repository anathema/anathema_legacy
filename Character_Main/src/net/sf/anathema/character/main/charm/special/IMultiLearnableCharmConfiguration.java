package net.sf.anathema.character.main.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.library.trait.Trait;

public interface IMultiLearnableCharmConfiguration extends ISpecialCharmConfiguration {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}