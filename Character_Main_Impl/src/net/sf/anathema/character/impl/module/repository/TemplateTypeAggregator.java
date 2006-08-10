package net.sf.anathema.character.impl.module.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;

public class TemplateTypeAggregator {

  private final ITemplateRegistry characterTemplateRegistry;

  public TemplateTypeAggregator(ITemplateRegistry characterTemplateRegistry) {
    this.characterTemplateRegistry = characterTemplateRegistry;
  }

  public ITemplateTypeAggregation[] aggregateTemplates(CharacterType type) {
    ICharacterExternalsTemplate[] templates = characterTemplateRegistry.getAllSupportedTemplates(type);
    Map<ITemplateType, TemplateTypeAggregation> aggregations = new LinkedHashMap<ITemplateType, TemplateTypeAggregation>();
    for (ICharacterExternalsTemplate template : templates) {
      TemplateTypeAggregation aggregation = aggregations.get(template.getTemplateType());
      if (aggregation == null) {
        aggregation = new TemplateTypeAggregation(template.getTemplateType(), template.getPresentationProperties());
        aggregations.put(template.getTemplateType(), aggregation);
      }
      aggregation.addSupportedEdition(template.getEdition());

    }
    return aggregations.values().toArray(new ITemplateTypeAggregation[0]);
  }
}