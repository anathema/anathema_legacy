package net.sf.anathema.character.impl.model.creation.bonus;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;

public class AdditionalMagicLearnPointManagement implements IAdditionalMagicLearnPointManagement {

  private final IAdditionalMagicLearnPoolCalculator[] calculators;

  public AdditionalMagicLearnPointManagement(IAdditionalMagicLearnPool[] pools, IGenericCharacter abstraction) {
    calculators = new IAdditionalMagicLearnPoolCalculator[pools.length];
    for (int index = 0; index < pools.length; index++) {
      calculators[index] = new AdditionalMagicLearnPoolCalculator(pools[index], abstraction);
    }
  }

  public List<IMagic> spendOn(List<IMagic> magicToHandle) {
    List<IMagic> unhandledMagic = new ArrayList<IMagic>(magicToHandle);
    for (IMagic magic : magicToHandle) {
      spendOn(magic, unhandledMagic);
    }
    return unhandledMagic;
  }

  private void spendOn(IMagic magic, List<IMagic> cloneList) {
    for (IAdditionalMagicLearnPoolCalculator calculator : calculators) {
      if (calculator.canSpendOn(magic)) {
        calculator.spendPointsFor(magic);
        cloneList.remove(magic);
      }
    }
  }

  public int getPointsSpent() {
    int pointsSpent = 0;
    for (IAdditionalMagicLearnPoolCalculator calculator : calculators) {
      pointsSpent += calculator.getPointsSpent();
    }
    return pointsSpent;
  }

  public int getAdditionalPointsAmount() {
    int totalPoints = 0;
    for (IAdditionalMagicLearnPoolCalculator calculator : calculators) {
      totalPoints += calculator.getTotalPoints();
    }
    return totalPoints;
  }

  public void clear() {
    for (IAdditionalMagicLearnPoolCalculator calculator : calculators) {
      calculator.reset();
    }
  }
}