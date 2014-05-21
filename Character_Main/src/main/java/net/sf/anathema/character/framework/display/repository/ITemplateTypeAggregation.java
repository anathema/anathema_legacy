package net.sf.anathema.character.framework.display.repository;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.template.presentation.IPresentationProperties;

public interface ITemplateTypeAggregation {

  TemplateType getTemplateType();

  IPresentationProperties getPresentationProperties();

  boolean contains(HeroTemplate template);
}