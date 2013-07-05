package net.sf.anathema.character.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.generic.caste.CasteType.NULL_CASTE_TYPE;
import static net.sf.anathema.character.generic.impl.persistence.SecondEdition.SECOND_EDITION;

public class ToCharacterButtonDto implements Function<DescriptiveFeatures, CharacterButtonDto> {
  private final Resources resources;

  public ToCharacterButtonDto(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CharacterButtonDto apply(DescriptiveFeatures input) {
    String text = input.getPrintName();
    CharacterIdentifier identifier = input.getIdentifier();
    ITemplateType templateType = input.getTemplateType();
    Identifier casteType = input.getCasteType();
    String details = getDetails(templateType);
    RelativePath pathToImage = getPathToImage(templateType, casteType);
    boolean dirty = input.isDirty();
    return new CharacterButtonDto(identifier, text, details, pathToImage, dirty);
  }

  private String getDetails(ITemplateType templateType) {
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
    presentationTemplate.setParentTemplate(templateType);
    return resources.getString(presentationTemplate.getNewActionResource());
  }

  private RelativePath getPathToImage(ITemplateType templateType, Identifier casteType) {
    if (casteType == NULL_CASTE_TYPE) {
      return new CharacterUI().getLargeTypeIconPath(templateType.getCharacterType());
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate();
      presentationTemplate.setParentTemplate(templateType);
      return presentationTemplate.getLargeCasteIconResource(casteType.getId(), SECOND_EDITION);
    }
  }
}
