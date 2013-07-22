package net.sf.anathema.hero.charms.model.special.multilearn;

public class StaticMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final int learnCount;

  public StaticMultiLearnableCharm(String charmId, int learnCount) {
    super(charmId);
    this.learnCount = learnCount;
  }

  @Override
  public int getAbsoluteLearnLimit() {
    return learnCount;
  }

  @Override
  public int getMaximumLearnCount(LearnRangeContext context) {
    return learnCount;
  }

  public String toString() {
    return "[" + getCharmId() + ";" + learnCount + "]";
  }
}