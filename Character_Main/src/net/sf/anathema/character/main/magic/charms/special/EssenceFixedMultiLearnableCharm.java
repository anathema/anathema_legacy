package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.traits.TraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(String charmId, int absoluteLearnLimit, TraitType traitType) {
    super(charmId, absoluteLearnLimit, traitType);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return getMaximumLearnCount(learnRangeContext);
  }
}