package net.sf.anathema.character.impl.model.creation.bonus.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacterStatistics;

public class AdditionalBonusPointPoolManagement implements IAdditionalBonusPointManagment {

  private final AdditionalBonusPointPoolCalculator[] additionalPoolCalculators;

  public AdditionalBonusPointPoolManagement(ICharacterStatistics statistics) {
    IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(statistics);
    IAdditionalRules additionalRules = statistics.getCharacterTemplate().getAdditionalRules();
    IAdditionalBonusPointPool[] pools = additionalRules.getAdditionalBonusPointPools();
    additionalPoolCalculators = new AdditionalBonusPointPoolCalculator[pools.length];
    for (int index = 0; index < pools.length; index++) {
      additionalPoolCalculators[index] = new AdditionalBonusPointPoolCalculator(
          pools[index],
          statistics.getTraitConfiguration(),
          character);
    }
  }

  public int getAmount() {
    int amount = 0;
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      amount += calculator.getAmount();
    }
    return amount;
  }

  public void reset() {
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      calculator.reset();
    }
  }

  public void spendOn(IGenericTrait trait, int bonusCost) {
    if (bonusCost == 0) {
      return;
    }
    int pointsToSpent = bonusCost;
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      pointsToSpent -= calculator.spend(trait, pointsToSpent);
    }
  }

  public void spendOn(IMagic magic, int bonusCost) {
    if (bonusCost == 0) {
      return;
    }
    int pointsToSpent = bonusCost;
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      pointsToSpent -= calculator.spend(magic, pointsToSpent);
    }
  }

  public int getPointSpent() {
    int pointsSpent = 0;
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      pointsSpent += calculator.getAmount() - calculator.getRemainingPoints();
    }
    return pointsSpent;
  }

  public void spendOn(IGenericSpecialty[] specialties, IAbilityPointCosts costs) {
    List<IGenericSpecialty> allSpecialties = new ArrayList<IGenericSpecialty>(Arrays.asList(specialties));
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      calculator.spend(allSpecialties, costs);
    }
  }

  public IDefaultTrait[] sortBackgrounds(IDefaultTrait[] backgrounds) {
    List<IDefaultTrait> sortedBackgrounds = new ArrayList<IDefaultTrait>();
    for (IDefaultTrait background : backgrounds) {
      if (!isFavoredBackground(background)) {
        sortedBackgrounds.add(background);
      }
    }
    for (IDefaultTrait background : backgrounds) {
      if (!sortedBackgrounds.contains(background)) {
        sortedBackgrounds.add(background);
      }
    }
    return sortedBackgrounds.toArray(new IDefaultTrait[sortedBackgrounds.size()]);
  }

  private boolean isFavoredBackground(IDefaultTrait background) {
    if (background.getType() == null) {
      return false;
    }
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      if (calculator.isFavoredBackground(background)) {
        return true;
      }
    }
    return false;
  }
}