package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private final IPointCostCalculator calculator;
  private final ICoreTraitConfiguration configuration;
  private final ICharacterStatistics statistics;

  public SpecialtyExperienceModel(
      ICoreTraitConfiguration configuration,
      IPointCostCalculator calculator,
      ICharacterStatistics statistics) {
    super("Experience", "Specialties"); //$NON-NLS-1$//$NON-NLS-2$
    this.configuration = configuration;
    this.calculator = calculator;
    this.statistics = statistics;
  }

  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    ISpecialtyConfiguration specialtyConfiguration = configuration.getSpecialtyConfiguration();
    for (ITraitType abilityType : getAllAbilityTypes()) {
      ISpecialtiesContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(abilityType);
      IFavorableModifiableTrait ability = configuration.getFavorableTrait(abilityType);
      experienceCosts += specialtiesContainer.getExperienceLearnedSpecialtyCount()
          * calculator.getSpecialtyCosts(ability.getFavorization().isCasteOrFavored());
    }
    return experienceCosts;
  }

  private ITraitType[] getAllAbilityTypes() {
    ITraitTypeGroup[] groups = configuration.getAbilityTypeGroups();
    return TraitTypeGroup.getAllTraitTypes(groups);
  }
}