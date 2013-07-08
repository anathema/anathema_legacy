package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.character.main.library.trait.subtrait.ISubTraitContainer;
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
