package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;

public class StaticPainToleranceCharm extends StaticMultiLearnableCharm implements IPainToleranceCharm {

  private final int[] painToleranceLevels;

  public StaticPainToleranceCharm(String charmId, int learnCount, int[] painToleranceLevels) {
    super(charmId, learnCount);
    Ensure.ensureTrue(
        "Number of pain tolerance levels must equal learn count.", painToleranceLevels.length == learnCount); //$NON-NLS-1$
    this.painToleranceLevels = painToleranceLevels;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    super.accept(visitor);
    visitor.visitPainToleranceCharm(this);
  }

  public int getPainToleranceLevel(int learnCount) {
    if (learnCount <= 0) {
      return 0;
    }
    return painToleranceLevels[learnCount - 1];
  }
}