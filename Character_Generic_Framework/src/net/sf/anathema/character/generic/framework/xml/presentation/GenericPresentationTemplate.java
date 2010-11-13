package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericPresentationTemplate extends ReflectionCloneableObject<GenericPresentationTemplate> implements
    IPresentationProperties {

  private GenericCharmPresentationProperties charmPresentationProperties;
  private Color color;
  private ICharacterTemplate template;

  public String getSmallCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + editionId + "16.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public Color getColor() {
    return color;
  }

  public String getNewActionResource() {
    return "CharacterGenerator.Templates." + getCharacterTypeId() + "." + getSubTypeId(); //$NON-NLS-1$//$NON-NLS-2$
  }

  public String getCasteLabelResource() {
    return getCharacterTypeId() + ".Caste.Label"; //$NON-NLS-1$;
  }

  public GenericCharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public GenericPresentationTemplate clone() {
    GenericPresentationTemplate clone = super.clone();
    if (clone.charmPresentationProperties != null) {
      clone.charmPresentationProperties = clone.charmPresentationProperties.clone();
    }
    return clone;
  }

  public void setParentTemplate(ICharacterTemplate template) {
    this.template = template;
  }

  private String getCharacterTypeId() {
    return template.getTemplateType().getCharacterType().getId();
  }

  private String getSubTypeId() {
    return template.getTemplateType().getSubType().getId();
  }

  public void setCharmPresentationProperties(GenericCharmPresentationProperties properties) {
    charmPresentationProperties = properties;
  }
}