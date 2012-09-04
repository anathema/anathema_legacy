package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;

public class TieredMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final CharmTier[] tiers;

  public TieredMultiLearnableCharm(String charmId, CharmTier[] tiers) {
    super(charmId);
    this.tiers = tiers;
  }

  public CharmTier[] getTiers() {
    return tiers;
  }

  @Override
  public int getAbsoluteLearnLimit() {
    return tiers.length;
  }

  @Override
  public int getMaximumLearnCount(LearnRangeContext context) {
    int learnLimit = 0;
    for (CharmTier tier : tiers) {
      if (tier.isLearnable(context)) {
        learnLimit++;
      }
    }
    return learnLimit;
  }
}