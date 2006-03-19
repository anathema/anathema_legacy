package net.sf.anathema.character.generic.impl.template.presentation;

import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

public abstract class AbstractPresentationProperties implements IPresentationProperties {

  private final TemplateType templateType;

  public AbstractPresentationProperties(TemplateType templateType) {
    this.templateType = templateType;
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

  public String getCasteResourceBase() {
    return getCharacterTypeId() + ".Caste."; //$NON-NLS-1$
  }

  protected final TemplateType getTemplateType() {
    return templateType;
  }
}