package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
import net.sf.anathema.character.generic.traits.ITraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(String charmId, int absoluteLearnLimit, ITraitType traitType) {
    super(charmId, absoluteLearnLimit, traitType);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return getMaximumLearnCount(learnRangeContext);
  }
}