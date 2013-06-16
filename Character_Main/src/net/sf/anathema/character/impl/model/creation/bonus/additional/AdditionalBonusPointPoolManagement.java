package net.sf.anathema.character.impl.model.creation.bonus.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.traits.model.TraitMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionalBonusPointPoolManagement implements IAdditionalBonusPointManagment {

  private final AdditionalBonusPointPoolCalculator[] additionalPoolCalculators;

  public AdditionalBonusPointPoolManagement(TraitMap traitCollection, IAdditionalBonusPointPool[] pools) {
    additionalPoolCalculators = new AdditionalBonusPointPoolCalculator[pools.length];
    for (int index = 0; index < pools.length; index++) {
      additionalPoolCalculators[index] = new AdditionalBonusPointPoolCalculator(pools[index], traitCollection);
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

  @Override
  public void spendOn(GenericTrait trait, int bonusCost) {
    if (bonusCost == 0) {
      return;
    }
    int pointsToSpent = bonusCost;
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      pointsToSpent -= calculator.spend(trait, pointsToSpent);
    }
  }

  @Override
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

  @Override
  public void spendOn(IGenericSpecialty[] specialties, AbilityPointCosts costs) {
    List<IGenericSpecialty> allSpecialties = new ArrayList<>(Arrays.asList(specialties));
    for (AdditionalBonusPointPoolCalculator calculator : additionalPoolCalculators) {
      calculator.spend(allSpecialties, costs);
    }
  }

  public Trait[] sortBackgrounds(Trait[] backgrounds) {
    List<Trait> sortedBackgrounds = new ArrayList<>();
    for (Trait background : backgrounds) {
      if (!isFavoredBackground(background)) {
        sortedBackgrounds.add(background);
      }
    }
    for (Trait background : backgrounds) {
      if (!sortedBackgrounds.contains(background)) {
        sortedBackgrounds.add(background);
      }
    }
    return sortedBackgrounds.toArray(new Trait[sortedBackgrounds.size()]);
  }

  private boolean isFavoredBackground(Trait background) {
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