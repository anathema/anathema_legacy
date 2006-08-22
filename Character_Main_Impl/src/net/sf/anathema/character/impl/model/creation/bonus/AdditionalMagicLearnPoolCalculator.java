package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;

public class AdditionalMagicLearnPoolCalculator implements IAdditionalMagicLearnPoolCalculator {

  private final IAdditionalMagicLearnPool pool;
  private final IGenericCharacter character;
  private int pointsSpent = 0;

  public AdditionalMagicLearnPoolCalculator(IAdditionalMagicLearnPool pool, IGenericCharacter abstraction) {
    this.pool = pool;
    this.character = abstraction;
  }

  public boolean canSpendOn(IMagic magic) {
    if (!pool.isAllowedFor(character.getTraitCollection(), magic)) {
      return false;
    }
    return pointsSpent < pool.getAdditionalMagicCount(character.getTraitCollection());
  }

  public void spendPointsFor(IMagic magic) {
    if (canSpendOn(magic)) {
      if (magic instanceof ICharm && character.isAlienCharm((ICharm) magic)) {
        pointsSpent++;
      }
      pointsSpent++;
    }
  }

  public int getPointsSpent() {
    return pointsSpent;
  }

  public int getTotalPoints() {
    return pool.getAdditionalMagicCount(character.getTraitCollection());
  }

  public void reset() {
    pointsSpent = 0;
  }
}