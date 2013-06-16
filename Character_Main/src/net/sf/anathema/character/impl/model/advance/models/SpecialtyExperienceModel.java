package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private final IPointCostCalculator calculator;
  private final ICoreTraitConfiguration configuration;

  public SpecialtyExperienceModel(ICoreTraitConfiguration configuration, IPointCostCalculator calculator) {
    super("Experience", "Specialties");
    this.configuration = configuration;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    ISpecialtiesConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
    for (ITraitType abilityType : getAllAbilityTypes()) {
      ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(abilityType);
      Trait ability = configuration.getTrait(abilityType);
      experienceCosts += specialtiesContainer.getExperienceDotTotal() * calculator.getSpecialtyCosts(ability.getFavorization().isCasteOrFavored());
    }
    return experienceCosts;
  }

  private ITraitType[] getAllAbilityTypes() {
    ITraitTypeGroup[] groups = configuration.getAbilityTypeGroups();
    return TraitTypeGroup.getAllTraitTypes(groups);
  }
}