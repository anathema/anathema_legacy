package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IAdditionalMagicLearnPoolCalculator {

  public boolean canSpendOn(IMagic magic);

  public void spendPointsFor(IMagic magic);

  public int getPointsSpent();

  public int getTotalPoints();

  public void reset();
}