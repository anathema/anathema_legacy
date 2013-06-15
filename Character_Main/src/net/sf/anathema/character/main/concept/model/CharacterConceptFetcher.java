package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.model.ICharacter;

public class CharacterConceptFetcher {

  public static CharacterConcept fetch(ICharacter character) {
    return character.getModel(CharacterConcept.ID);
  }
}
