package net.sf.anathema.character.main.magic.charms.special;

import com.google.common.base.Preconditions;

public class StaticPainToleranceCharm extends StaticMultiLearnableCharm implements IPainToleranceCharm {

  private final int[] painToleranceLevels;

  public StaticPainToleranceCharm(String charmId, int learnCount, int[] painToleranceLevels) {
    super(charmId, learnCount);
    Preconditions.checkArgument(painToleranceLevels.length == learnCount, "Number of pain tolerance levels must equal learn count.");
    this.painToleranceLevels = painToleranceLevels;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    super.accept(visitor);
    visitor.visitPainToleranceCharm(this);
  }

  @Override
  public int getPainToleranceLevel(int learnCount) {
    if (learnCount <= 0) {
      return 0;
    }
    return painToleranceLevels[learnCount - 1];
  }
}