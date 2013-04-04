package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.resources.IStringResourceHandler;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;
import static net.sf.anathema.character.generic.impl.persistence.SecondEdition.SECOND_EDITION;

public class ToCharacterButtonDto implements Function<DescriptiveFeatures, CharacterButtonDto> {
  private final IStringResourceHandler resources;

  public ToCharacterButtonDto(IStringResourceHandler resources) {
    this.resources = resources;
  }

  @Override
  public CharacterButtonDto apply(DescriptiveFeatures input) {
    String text = input.getPrintName();
    CharacterIdentifier identifier = input.getIdentifier();
    ITemplateType templateType = input.getTemplateType();
    Identified casteType = input.getCasteType();
    String details = getDetails(templateType);
    String pathToImage = getPathToImage(templateType, casteType);
    boolean dirty = input.isDirty();
    return new CharacterButtonDto(identifier, text, details, pathToImage, dirty);
  }

  private String getDetails(ITemplateType templateType) {
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
    presentationTemplate.setParentTemplate(templateType);
    return resources.getString(presentationTemplate.getNewActionResource());
  }

  private String getPathToImage(ITemplateType templateType, Identified casteType) {
    StringBuilder imagePath = new StringBuilder();
    if (casteType == NULL_CASTE_TYPE) {
      imagePath.append(new CharacterUI().getLargeTypeIconPath(templateType.getCharacterType()));
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
      presentationTemplate.setParentTemplate(templateType);
      String casteIcon = presentationTemplate.getLargeCasteIconResource(casteType.getId(), SECOND_EDITION);
      imagePath.append(casteIcon);
    }
    return imagePath.toString();
  }
}
