package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.resources.IStringResourceHandler;
import net.sf.anathema.lib.util.Identified;

import javax.annotation.Nullable;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;
import static net.sf.anathema.character.impl.persistence.SecondEdition.SECOND_EDITION;

public class ToCharacterButtonDto implements Function<DistinctiveFeatures, CharacterButtonDto> {
  private final IStringResourceHandler resources;

  public ToCharacterButtonDto(IStringResourceHandler resources) {
    this.resources = resources;
  }

  @Nullable
  @Override
  public CharacterButtonDto apply(@Nullable DistinctiveFeatures input) {
    String text = input.getPrintName();
    CharacterIdentifier identifier = input.getIdentifier();
    ITemplateType templateType = input.getTemplateType();
    Identified casteType = input.getCasteType();
    String details = getDetails(resources, templateType);
    String pathToImage = getPathToImage(templateType, casteType);
    return new CharacterButtonDto(identifier, text, details, pathToImage);
  }

  public static String getDetails(IStringResourceHandler resources, ITemplateType templateType) {
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
    presentationTemplate.setParentTemplate(templateType);
    return resources.getString(presentationTemplate.getNewActionResource());
  }

  public static String getPathToImage(ITemplateType templateType, Identified casteType) {
    StringBuilder imagePath = new StringBuilder("icons/");
    if (casteType == NULL_CASTE_TYPE) {
      imagePath.append(new CharacterUI(null).getLargeTypeIconPath(templateType.getCharacterType()));
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
      presentationTemplate.setParentTemplate(templateType);
      String casteIcon = presentationTemplate.getLargeCasteIconResource(casteType.getId(), SECOND_EDITION);
      imagePath.append(casteIcon);
    }
    return imagePath.toString();
  }
}
