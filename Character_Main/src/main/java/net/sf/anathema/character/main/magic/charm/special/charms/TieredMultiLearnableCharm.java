package net.sf.anathema.character.main.magic.charm.special.charms;

import net.sf.anathema.character.main.magic.charm.special.CharmTier;
import net.sf.anathema.character.main.magic.charm.special.LearnRangeContext;
import net.sf.anathema.character.main.magic.charm.special.charms.AbstractMultiLearnableCharm;

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