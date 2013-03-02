package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

public class CharacterButtonDto {

  public final CharacterIdentifier identifier;
  public final String text;
  public final String details;
  public final String pathToImage;

  public CharacterButtonDto(CharacterIdentifier identifier, String text, String details, String pathToImage) {
    this.identifier = identifier;
    this.text = text;
    this.details = details;
    this.pathToImage = pathToImage;
  }
}