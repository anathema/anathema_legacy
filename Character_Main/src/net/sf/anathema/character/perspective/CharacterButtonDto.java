package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;

public class CharacterButtonDto {

  public final CharacterIdentifier identifier;
  public final String text;

  public CharacterButtonDto(CharacterIdentifier identifier, String text) {
    this.identifier = identifier;
    this.text = text;
  }
}
