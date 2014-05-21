package net.sf.anathema.hero.framework.perspective;

import com.google.common.base.Function;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.hero.concept.CasteType.NULL_CASTE_TYPE;

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
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate(templateType);
    return resources.getString(presentationTemplate.getNewActionResource());
  }

  private RelativePath getPathToImage(ITemplateType templateType, Identifier casteType) {
    if (casteType == NULL_CASTE_TYPE) {
      return new CharacterUI().getLargeTypeIconPath(templateType.getCharacterType());
    } else {
      GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate(templateType);
      return presentationTemplate.getLargeCasteIconResource(casteType.getId());
    }
  }
}
