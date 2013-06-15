package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.ICharacter;

public class CharacterDescriptionExtractor {

  public static ICharacterDescription getCharacterDescription(ICharacter character) {
    return character.getModel(ICharacterDescription.ID);
  }
}
