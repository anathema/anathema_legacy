package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.hero.specialties.SpecialtiesModel;
import net.sf.anathema.hero.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.specialties.ISubTraitContainer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.model.Hero;

public class SpecialtiesContentCandidate {

  private Hero hero;

  public SpecialtiesContentCandidate(Hero hero) {
    this.hero = hero;
  }

  public Specialty[] getSpecialties(TraitType traitType) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(traitType);
    return specialtiesContainer.getSubTraits();
  }
}
