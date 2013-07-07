package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.library.trait.Trait;

public interface IMultiLearnableCharmConfiguration extends ISpecialCharmConfiguration {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}