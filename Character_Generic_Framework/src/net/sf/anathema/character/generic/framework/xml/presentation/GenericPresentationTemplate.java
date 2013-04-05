package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericPresentationTemplate extends ReflectionCloneableObject<GenericPresentationTemplate> implements
        IPresentationProperties {

  private GenericCharmPresentationProperties charmPresentationProperties = new GenericCharmPresentationProperties();
  private ITemplateType templateType;

  @Override
  public RelativePath getSmallCasteIconResource(String casteId, String editionId) {
    return new RelativePath("icons/" + getCharacterTypeId() + "Button" + casteId + editionId + "16.png");
  }

  @Override
  public RelativePath getLargeCasteIconResource(String casteId, String editionId) {
    return new RelativePath("icons/" + getCharacterTypeId() + "Button" + casteId + editionId + "100.png");
  }

  @Override
  public String getNewActionResource() {
    return "CharacterGenerator.Templates." + getCharacterTypeId() + "." + getSubTypeId();
  }

  @Override
  public String getCasteLabelResource() {
    return getCharacterTypeId() + ".Caste.Label";
  }

  @Override
  public GenericCharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }

  @Override
  public GenericPresentationTemplate clone() {
    GenericPresentationTemplate clone = super.clone();
    if (clone.charmPresentationProperties != null) {
      clone.charmPresentationProperties = clone.charmPresentationProperties.clone();
    }
    return clone;
  }

  public void setParentTemplate(ITemplateType templateType) {
    this.templateType = templateType;
  }

  public void setParentTemplate(ICharacterTemplate template) {
    this.templateType = template.getTemplateType();
  }

  private String getCharacterTypeId() {
    return templateType.getCharacterType().getId();
  }

  private String getSubTypeId() {
    return templateType.getSubType().getId();
  }
}