package net.sf.anathema.character.main.model.concept;

import net.sf.anathema.character.main.hero.Hero;

public class CharacterConceptFetcher {

  public static CharacterConcept fetch(Hero hero) {
    return (CharacterConcept) hero.getModel(CharacterConcept.ID);
  }
}
