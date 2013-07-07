package net.sf.anathema.hero.concept;

import net.sf.anathema.hero.model.Hero;

public class HeroConceptFetcher {

  public static HeroConcept fetch(Hero hero) {
    return hero.getModel(HeroConcept.ID);
  }
}
