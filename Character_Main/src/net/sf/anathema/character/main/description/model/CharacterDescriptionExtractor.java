package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.ICharacter;

public class CharacterDescriptionExtractor {

  public static CharacterDescription getCharacterDescription(ICharacter character) {
    return character.getModel(CharacterDescription.ID);
  }
}
