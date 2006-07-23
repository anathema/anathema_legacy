package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericPresentationTemplate extends ReflectionCloneableObject<GenericPresentationTemplate> implements
    IPresentationProperties {

  private GenericCharmPresentationProperties charmPresentationProperties;
  private Color color;
  private String newActionResource;
  private GenericCharacterTemplate template;

  public String getMediumCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + editionId + "20.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public String getSmallCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + editionId + "16.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  public Color getColor() {
    return color;
  }

  public String getNewActionResource() {
    return newActionResource;
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

  public void setNewActionResource(String newActionResource) {
    this.newActionResource = newActionResource;
  }

  @Override
  public GenericPresentationTemplate clone() {
    GenericPresentationTemplate clone = super.clone();
    if (clone.charmPresentationProperties != null) {
      clone.charmPresentationProperties = clone.charmPresentationProperties.clone();
    }
    return clone;
  }

  public void setParentTemplate(GenericCharacterTemplate template) {
    this.template = template;
  }

  private String getCharacterTypeId() {
    return template.getTemplateType().getCharacterType().getId();
  }

  public void setCharmPresentationProperties(GenericCharmPresentationProperties properties) {
    charmPresentationProperties = properties;
  }
}