package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;

import javax.annotation.Nullable;

public class ToCharacterButtonDto implements Function<PrintNameFile, CharacterButtonDto> {
  @Nullable
  @Override
  public CharacterButtonDto apply(@Nullable PrintNameFile input) {
    String text = input.getPrintName();
    String repositoryId = input.getRepositoryId();
    CharacterIdentifier identifier = new CharacterIdentifier(repositoryId);
    return new CharacterButtonDto(identifier, text);
  }
}
