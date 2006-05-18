package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericPresentationTemplate extends ReflectionCloneableObject implements IPresentationProperties {

  private GenericCharmPresentationProperties charmPresentationProperties;
  private Color color;
  private String ballResource;
  private String newActionResource;
  private GenericCharacterTemplate template;

  public String getBallResource() {
    return ballResource;
  }

  public String getMediumCasteIconResource(String casteId) {
    return getCharacterType().getId() + "Button" + casteId + "20.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public String getSmallCasteIconResource(String casteId) {
    return getCharacterType().getId() + "Button" + casteId + "16.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public Color getColor() {
    return color;
  }

  public String getNewActionResource() {
    return newActionResource;
  }

  public String getCasteLabelResource() {
    return getCharacterType().getId() + ".Caste.Label"; //$NON-NLS-1$;
  }

  public GenericCharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setBallResource(String ballResource) {
    this.ballResource = ballResource;
  }

  public void setNewActionResource(String newActionResource) {
    this.newActionResource = newActionResource;
  }

  @Override
  public GenericPresentationTemplate clone() {
    GenericPresentationTemplate clone = (GenericPresentationTemplate) super.clone();
    if (clone.charmPresentationProperties != null) {
      clone.charmPresentationProperties = (GenericCharmPresentationProperties) clone.charmPresentationProperties.clone();
    }
    return clone;
  }

  public void setParentTemplate(GenericCharacterTemplate template) {
    this.template = template;
  }

  private CharacterType getCharacterType() {
    return template.getTemplateType().getCharacterType();
  }

  public void setCharmPresentationProperties(GenericCharmPresentationProperties properties) {
    charmPresentationProperties = properties;
  }
}