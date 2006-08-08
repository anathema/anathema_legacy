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
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
import net.sf.anathema.character.library.trait.specialty.ISubTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class AbilityCostCalculator extends AbstractFavorableTraitCostCalculator implements IAbilityCostCalculator {

  private static IFavorableModifiableTrait[] getAllAbilities(ICoreTraitConfiguration traitConfiguration) {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return traitConfiguration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }

  private final IAbilityPointCosts costs;
  private final IFavorableModifiableTrait[] abilities;
  private int specialtyBonusPointCosts;
  private SpecialtyCalculator specialtyCalculator;
  private final IAdditionalSpecialtyBonusPointManagement additionalPools;
  private final ICoreTraitConfiguration traitConfiguration;

  public AbilityCostCalculator(
      ICoreTraitConfiguration traitConfiguration,
      IFavorableTraitCreationPoints points,
      IAbilityPointCosts costs,
      IAdditionalBonusPointManagment additionalPools) {
    super(additionalPools, points, getAllAbilities(traitConfiguration));
    this.traitConfiguration = traitConfiguration;
    this.abilities = getAllAbilities(traitConfiguration);
    this.costs = costs;
    this.additionalPools = additionalPools;
    this.specialtyCalculator = new SpecialtyCalculator(traitConfiguration);
  }

  @Override
  public void calculateCosts() {
    super.calculateCosts();
    calculateSpecialtyCosts();
  }

  @Override
  protected int getCostFactor(IFavorableModifiableTrait ability) {
    ITraitFavorization favorization = ability.getFavorization();
    int costFactor = costs.getAbilityCosts(favorization.isCasteOrFavored()).getRatingCosts(
        ability.getCalculationValue());
    return costFactor;
  }

  private void calculateSpecialtyCosts() {
    IGenericSpecialty[] specialties = createGenericSpecialties();
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
    additionalPools.spendOn(specialties, costs);
  }

  private IGenericSpecialty[] createGenericSpecialties() {
    List<IGenericSpecialty> specialties = new ArrayList<IGenericSpecialty>();
    for (IFavorableModifiableTrait ability : abilities) {
      ISpecialtyConfiguration specialtyConfiguration = traitConfiguration.getSpecialtyConfiguration();
      for (ISubTrait specialty : specialtyConfiguration.getSpecialtiesContainer(ability.getType()).getSubTraits()) {
        for (int index = 0; index < specialty.getCalculationValue(); index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[0]);
  }

  @Override
  protected void clear() {
    super.clear();
    specialtyBonusPointCosts = 0;
  }

  public int getSpecialtyBonusPointCosts() {
    return specialtyBonusPointCosts;
  }
}