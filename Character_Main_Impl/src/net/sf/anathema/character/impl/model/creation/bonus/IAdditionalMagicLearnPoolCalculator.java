package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IAdditionalMagicLearnPoolCalculator {

  boolean canSpendOn(IMagic magic);

  void spendPointsFor(IMagic magic);

  int getPointsSpent();

  int getTotalPoints();

  void reset();
}