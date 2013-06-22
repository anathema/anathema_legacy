package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.hero.model.Hero;

public class SpecialtiesModelFetcher {

  public static SpecialtiesModel fetch(Hero hero) {
    return hero.getModel(SpecialtiesModel.ID);
  }
}
