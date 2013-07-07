package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.presentation.ICommonPresentationProperties;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;

public class TemplateTypeAggregation implements ITemplateTypeAggregation {

  private final ITemplateType templateType;
  private final ICommonPresentationProperties properties;

  public TemplateTypeAggregation(ITemplateType templateType, ICommonPresentationProperties properties) {
    this.templateType = templateType;
    this.properties = properties;
  }

  @Override
  public boolean contains(HeroTemplate template) {
    return templateType.equals(template.getTemplateType());
  }

  @Override
  public ITemplateType getTemplateType() {
    return templateType;
  }

  @Override
  public ICommonPresentationProperties getPresentationProperties() {
    return properties;
  }
}