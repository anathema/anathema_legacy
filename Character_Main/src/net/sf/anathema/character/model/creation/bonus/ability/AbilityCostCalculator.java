package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTraitCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.character.model.creation.bonus.additional.IAdditionalBonusPointManagement;
import net.sf.anathema.character.model.creation.bonus.additional.IAdditionalSpecialtyBonusPointManagement;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class AbilityCostCalculator extends AbstractFavorableTraitCostCalculator implements IAbilityCostCalculator {

  private final IAdditionalSpecialtyBonusPointManagement additionalPools;
  private Hero hero;
  private final AbilityPointCosts costs;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;
  private SpecialtyCalculator specialtyCalculator;

  public AbilityCostCalculator(Hero hero, AbilitiesModel abilitiesModel, IFavorableTraitCreationPoints points, int specialtyPoints,
                               AbilityPointCosts costs, IAdditionalBonusPointManagement additionalPools) {
    super(additionalPools, points, costs.getMaximumFreeAbilityRank(), abilitiesModel.getAll());
    this.hero = hero;
    this.costs = costs;
    this.additionalPools = additionalPools;
    this.specialtyCalculator = new SpecialtyCalculator(abilitiesModel, specialtyPoints);
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
    List<IGenericSpecialty> specialties = new ArrayList<>();
    for (Trait ability : getTraits()) {
      SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
      ISubTraitContainer specialtiesContainer = specialtiesModel.getSpecialtiesContainer(ability.getType());
      for (Specialty specialty : specialtiesContainer.getSubTraits()) {
        for (int index = 0; index < specialty.getCalculationValue(); index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[specialties.size()]);
  }

  @Override
  protected int getCostFactor(Trait trait) {
    ITraitFavorization favorization = trait.getFavorization();
    return costs.getAbilityCosts(favorization.isCasteOrFavored()).getRatingCosts(trait.getCalculationValue());
  }

  @Override
  public int getSpecialtyBonusPointCosts() {
    return specialtyBonusPointCosts;
  }

  @Override
  public int getFreeSpecialtyPointsSpent() {
    return specialtyDotSum;
  }
}