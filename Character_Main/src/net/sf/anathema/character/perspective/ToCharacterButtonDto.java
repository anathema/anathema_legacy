package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identified;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.IOException;

public class ToCharacterButtonDto implements Function<PrintNameFile, CharacterButtonDto> {
  private CharacterPrintNameFileScanner fileScanner;

  public ToCharacterButtonDto(CharacterPrintNameFileScanner fileScanner) {
    this.fileScanner = fileScanner;
  }

  @Nullable
  @Override
  public CharacterButtonDto apply(@Nullable PrintNameFile input) {
    String text = input.getPrintName();
    String repositoryId = input.getRepositoryId();
    CharacterIdentifier identifier = new CharacterIdentifier(repositoryId);
    ICharacterType characterType = fileScanner.getCharacterType(input);
    String details = characterType.getId();
    return new CharacterButtonDto(identifier, text, details);
  }
}
