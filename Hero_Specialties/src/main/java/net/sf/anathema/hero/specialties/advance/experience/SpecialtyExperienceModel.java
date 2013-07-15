package net.sf.anathema.hero.specialties.advance.experience;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.main.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.model.Hero;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private Hero hero;

  public SpecialtyExperienceModel(Hero hero) {
    super("Experience", "Specialties");
    this.hero = hero;
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (Trait ability : AbilityModelFetcher.fetch(hero).getAll()) {
      experienceCosts += getExperienceDots(ability) * getCostPerSpecialtyDot(ability);
    }
    return experienceCosts;
  }

  private int getExperienceDots(Trait ability) {
    ISubTraitContainer specialtiesContainer = getSpecialtyContainer(ability);
    return specialtiesContainer.getExperienceDotTotal();
  }

  private ISubTraitContainer getSpecialtyContainer(Trait ability) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    return specialtyConfiguration.getSpecialtiesContainer(ability.getType());
  }

  private double getCostPerSpecialtyDot(Trait ability) {
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    boolean casteOrFavored = ability.getFavorization().isCasteOrFavored();
    return experienceCost.getSpecialtyCosts(casteOrFavored);
  }
}