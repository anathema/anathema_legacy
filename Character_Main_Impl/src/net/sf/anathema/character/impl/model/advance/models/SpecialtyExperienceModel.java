package net.sf.anathema.character.impl.model.advance.models;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class SpecialtyExperienceModel implements IValueModel<Integer> {

  private final IPointCostCalculator calculator;
  private final ICoreTraitConfiguration configuration;
  private final ICharacterStatistics statistics;

  public SpecialtyExperienceModel(
      ICoreTraitConfiguration configuration,
      IPointCostCalculator calculator,
      ICharacterStatistics statistics) {
    this.configuration = configuration;
    this.calculator = calculator;
    this.statistics = statistics;
  }

  public Integer getValue() {
    return getSpecialtyCosts();
  }

  public String getId() {
    return "Specialties"; //$NON-NLS-1$
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait ability : getAllAbilities()) {
      experienceCosts += ability.getSpecialtiesContainer().getExperienceLearnedSpecialtyCount()
          * calculator.getSpecialtyCosts(ability.getFavorization().isCasteOrFavored());
    }
    return experienceCosts;
  }

  private IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IGroupedTraitType type : statistics.getCharacterTemplate().getAbilityGroups()) {
      abilityTypes.add(type.getTraitType());
    }
    return configuration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }
}