package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface IMultiLearnableCharm extends ISpecialCharm {

  public int getAbsoluteLearnLimit();
  
  public int getMinimumLearnCount(IGenericTraitCollection traitCollection);

  public int getMaximumLearnCount(IGenericTraitCollection traitCollection);
}