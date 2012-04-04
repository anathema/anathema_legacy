package net.sf.anathema.character.view.repository;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICommonPresentationProperties;

public interface ITemplateTypeAggregation {

  ITemplateType getTemplateType();

  ICommonPresentationProperties getPresentationProperties();

  boolean contains(ICharacterTemplate template);
}