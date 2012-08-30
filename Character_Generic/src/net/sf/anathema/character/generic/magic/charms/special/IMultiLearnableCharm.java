package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;

public interface IMultiLearnableCharm extends ISpecialCharm {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(GenericTraitProvider traitCollection);

  int getMaximumLearnCount(GenericTraitProvider traitCollection);
}