package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IStringResourceHandler;
import net.sf.anathema.lib.util.Identified;

import javax.annotation.Nullable;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;
import static net.sf.anathema.character.impl.persistence.SecondEdition.SECOND_EDITION;

public class ToCharacterButtonDto implements Function<PrintNameFile, CharacterButtonDto> {
  private final CharacterPrintNameFileScanner fileScanner;
  private final IStringResourceHandler resources;

  public ToCharacterButtonDto(CharacterPrintNameFileScanner fileScanner, IStringResourceHandler resources) {
    this.fileScanner = fileScanner;
    this.resources = resources;
  }

  @Nullable
  @Override
  public CharacterButtonDto apply(@Nullable PrintNameFile input) {
    String text = input.getPrintName();
    String repositoryId = input.getRepositoryId();
    CharacterIdentifier identifier = new CharacterIdentifier(repositoryId);
    ITemplateType templateType = fileScanner.getTemplateType(input);
    Identified casteType = fileScanner.getCasteType(input);
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
    presentationTemplate.setParentTemplate(templateType);
    String details = resources.getString(presentationTemplate.getNewActionResource());
    String pathToImage = getPathToImage(templateType, casteType);
    return new CharacterButtonDto(identifier, text, details, pathToImage);
  }

  private String getPathToImage(ITemplateType templateType, Identified casteType) {
    StringBuilder imagePath = new StringBuilder("icons/");
    if (casteType == NULL_CASTE_TYPE) {
      imagePath.append(new CharacterUI(null).getSmallTypeIconPath(templateType.getCharacterType()));
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
      presentationTemplate.setParentTemplate(templateType);
      String casteIcon = presentationTemplate.getSmallCasteIconResource(casteType.getId(), SECOND_EDITION);
      imagePath.append(casteIcon);
    }
    return imagePath.toString();
  }
}
