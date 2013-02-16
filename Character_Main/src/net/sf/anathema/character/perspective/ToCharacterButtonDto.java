package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identified;

import javax.annotation.Nullable;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;

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
    Identified casteType = fileScanner.getCasteType(input);
    String details = characterType.getId();
    String pathToImage = getPathToImage(characterType, casteType);
    return new CharacterButtonDto(identifier, text, details, pathToImage);
  }

  private String getPathToImage(ICharacterType characterType, Identified casteType) {
    StringBuilder imagePath = new StringBuilder("icons/");
    imagePath.append(characterType.getId());
    if (casteType == NULL_CASTE_TYPE) {
      String characterTypeSymbol = "Icon";
      imagePath.append(characterTypeSymbol);
    } else {
      String casteSymbol = "Button" + casteType.getId() + "SecondEdition";
      imagePath.append(casteSymbol);
    }
    imagePath.append("16.png");
    return imagePath.toString();
  }
}
