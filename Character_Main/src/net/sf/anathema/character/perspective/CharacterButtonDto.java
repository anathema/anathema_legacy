package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.file.RelativePath;

public class CharacterButtonDto {

  public final CharacterIdentifier identifier;
  public final String text;
  public final String details;
  public final RelativePath pathToImage;
  public final boolean isDirty;

  public CharacterButtonDto(CharacterIdentifier identifier, String text, String details, RelativePath pathToImage, boolean isDirty) {
    this.identifier = identifier;
    this.text = text;
    this.details = details;
    this.pathToImage = pathToImage;
    this.isDirty = isDirty;
  }
}