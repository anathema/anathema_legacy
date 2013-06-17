package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.abilities.AbilityModel;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private final IPointCostCalculator calculator;
  private final AbilityModel abilityModel;

  public SpecialtyExperienceModel(AbilityModel abilityModel, IPointCostCalculator calculator) {
    super("Experience", "Specialties");
    this.abilityModel = abilityModel;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (Trait ability : abilityModel.getAll()) {
      experienceCosts += getExperienceDots(ability) * getCostPerSpecialtyDot(ability);
    }
    return experienceCosts;
  }

  private int getExperienceDots(Trait ability) {
    ISubTraitContainer specialtiesContainer = getSpecialtyContainer(ability);
    return specialtiesContainer.getExperienceDotTotal();
  }

  private ISubTraitContainer getSpecialtyContainer(Trait ability) {
    ISpecialtiesConfiguration specialtyConfiguration = abilityModel.getSpecialtyConfiguration();
    return specialtyConfiguration.getSpecialtiesContainer(ability.getType());
  }

  private double getCostPerSpecialtyDot(Trait ability) {
    return calculator.getSpecialtyCosts(ability.getFavorization().isCasteOrFavored());
  }
}