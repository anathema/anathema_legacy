package net.sf.anathema.character.impl.model.creation.bonus.ability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalBonusPointManagment;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalSpecialtyBonusPointManagement;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTraitCostCalculator;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class AbilityCostCalculator extends AbstractFavorableTraitCostCalculator implements IAbilityCostCalculator {

  private static IFavorableTrait[] getAllAbilities(ICoreTraitConfiguration traitConfiguration) {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return traitConfiguration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }

  private final IAdditionalSpecialtyBonusPointManagement additionalPools;
  private final ICoreTraitConfiguration traitConfiguration;
  private final IAbilityPointCosts costs;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;
  private SpecialtyCalculator specialtyCalculator;

  public AbilityCostCalculator(
      ICoreTraitConfiguration traitConfiguration,
      IFavorableTraitCreationPoints points,
      int specialtyPoints,
      IAbilityPointCosts costs,
      IAdditionalBonusPointManagment additionalPools) {
    super(additionalPools, points, costs.getMaximumFreeAbilityRank(), getAllAbilities(traitConfiguration));
    this.traitConfiguration = traitConfiguration;
    this.costs = costs;
    this.additionalPools = additionalPools;
    this.specialtyCalculator = new SpecialtyCalculator(traitConfiguration, specialtyPoints);
  }

  @Override
  public void calculateCosts() {
    super.calculateCosts();
    calculateSpecialtyCosts();
  }

  private void calculateSpecialtyCosts() {
    IGenericSpecialty[] specialties = createGenericSpecialties();
    specialtyDotSum = specialtyCalculator.getSpecialtyPointsSpent(specialties);
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
    additionalPools.spendOn(specialties, costs);
  }

  @Override
  protected void clear() {
    super.clear();
    specialtyDotSum = 0;
    specialtyBonusPointCosts = 0;
  }

  private IGenericSpecialty[] createGenericSpecialties() {
    List<IGenericSpecialty> specialties = new ArrayList<IGenericSpecialty>();
    for (IFavorableTrait ability : getTraits()) {
      ISpecialtiesConfiguration specialtyConfiguration = traitConfiguration.getSpecialtyConfiguration();
      for (ISubTrait specialty : specialtyConfiguration.getSpecialtiesContainer(ability.getType()).getSubTraits()) {
        for (int index = 0; index < specialty.getCalculationValue(); index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[0]);
  }

  @Override
  protected int getCostFactor(IFavorableDefaultTrait trait) {
    ITraitFavorization favorization = trait.getFavorization();
    int costFactor = costs.getAbilityCosts(favorization.isCasteOrFavored()).getRatingCosts(trait.getCalculationValue());
    return costFactor;
  }

  public int getSpecialtyBonusPointCosts() {
    return specialtyBonusPointCosts;
  }
  
  public int getFreeSpecialtyPointsSpent() {
	  return specialtyDotSum;
  }
}