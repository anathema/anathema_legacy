package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.model.Hero;

public class CharacterConceptFetcher {

  public static CharacterConcept fetch(Hero hero) {
    return hero.getModel(CharacterConcept.ID);
  }
}
