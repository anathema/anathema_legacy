package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;

public class TemplateTypeAggregation implements ITemplateTypeAggregation {

  private final ITemplateType templateType;
  private final IPresentationProperties properties;

  public TemplateTypeAggregation(ITemplateType templateType, IPresentationProperties properties) {
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
  public IPresentationProperties getPresentationProperties() {
    return properties;
  }
}