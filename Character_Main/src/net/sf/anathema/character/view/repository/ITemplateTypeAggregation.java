package net.sf.anathema.character.view.repository;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICommonPresentationProperties;

public interface ITemplateTypeAggregation {

  public IExaltedEdition[] getSupportedEditions();

  public ITemplateType getTemplateType();

  public ICommonPresentationProperties getPresentationProperties();
}