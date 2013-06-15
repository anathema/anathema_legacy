package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.ICharacter;

public class CharacterDescriptionFetcher {

  public static CharacterDescription fetch(ICharacter character) {
    return character.getModel(CharacterDescription.ID);
  }
}
