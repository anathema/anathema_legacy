package net.sf.anathema.character.main.view.repository;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICommonPresentationProperties;

public interface ITemplateTypeAggregation {

  ITemplateType getTemplateType();

  ICommonPresentationProperties getPresentationProperties();

  boolean contains(HeroTemplate template);
}