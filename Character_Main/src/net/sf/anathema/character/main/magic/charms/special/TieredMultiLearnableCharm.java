package net.sf.anathema.character.main.magic.charms.special;

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