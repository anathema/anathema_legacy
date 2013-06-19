package net.sf.anathema.character.main.model.concept;

import net.sf.anathema.character.main.hero.Hero;

public class HeroConceptFetcher {

  public static HeroConcept fetch(Hero hero) {
    return hero.getModel(HeroConcept.ID);
  }
}
