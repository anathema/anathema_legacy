package net.sf.anathema.character.model.creation.bonus;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
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

  @Override
  public boolean canSpendOn(IMagic magic) {
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    if (!pool.isAllowedFor(traitCollection, magic)) {
      return false;
    }
    return pointsSpent < pool.getAdditionalMagicCount(traitCollection);
  }

  @Override
  public void spendPointsFor(IMagic magic) {
    if (canSpendOn(magic)) {
      if (magic instanceof ICharm && character.isAlienCharm((ICharm) magic)) {
        pointsSpent++;
      }
      pointsSpent++;
    }
  }

  @Override
  public int getPointsSpent() {
    return pointsSpent;
  }

  @Override
  public int getTotalPoints() {
    return pool.getAdditionalMagicCount(character.getTraitCollection());
  }

  @Override
  public void reset() {
    pointsSpent = 0;
  }
}