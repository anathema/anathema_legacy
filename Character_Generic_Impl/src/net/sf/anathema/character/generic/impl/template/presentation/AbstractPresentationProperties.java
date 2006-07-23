package net.sf.anathema.character.generic.impl.template.presentation;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

public abstract class AbstractPresentationProperties implements IPresentationProperties {

  private final ITemplateType templateType;

  public AbstractPresentationProperties(ITemplateType templateType) {
    this.templateType = templateType;
  }

  public String getMediumCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + "20.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public String getSmallCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + "16.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public String getNewActionResource() {
    return "CharacterGenerator.NewCharacter." + getCharacterTypeId() + ".Name"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected final String getCharacterTypeId() {
    return templateType.getCharacterType().getId();
  }

  public String getCasteLabelResource() {
    return "Label.Caste"; //$NON-NLS-1$
  }

  protected final ITemplateType getTemplateType() {
    return templateType;
  }
}