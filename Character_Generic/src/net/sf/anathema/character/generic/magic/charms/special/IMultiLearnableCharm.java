package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface IMultiLearnableCharm extends ISpecialCharm {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(IGenericTraitCollection traitCollection);

  int getMaximumLearnCount(IGenericTraitCollection traitCollection);
}