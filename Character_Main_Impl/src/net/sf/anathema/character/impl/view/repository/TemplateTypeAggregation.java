package net.sf.anathema.character.impl.view.repository;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICommonPresentationProperties;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;

public class TemplateTypeAggregation implements ITemplateTypeAggregation {

  private final ITemplateType templateType;
  private final Set<IExaltedEdition> editions = new HashSet<IExaltedEdition>();
  private final ICommonPresentationProperties properties;

  public TemplateTypeAggregation(ITemplateType templateType, ICommonPresentationProperties properties) {
    this.templateType = templateType;
    this.properties = properties;
  }

  public void addSupportedEdition(IExaltedEdition edition) {
    editions.add(edition);
  }

  public IExaltedEdition[] getSupportedEditions() {
    return editions.toArray(new IExaltedEdition[editions.size()]);
  }

  public ITemplateType getTemplateType() {
    return templateType;
  }

  public ICommonPresentationProperties getPresentationProperties() {
    return properties;
  }
}