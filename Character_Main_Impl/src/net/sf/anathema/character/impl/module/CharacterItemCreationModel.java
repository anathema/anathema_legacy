package net.sf.anathema.character.impl.module;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.view.repository.TemplateTypeAggregator;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private CharacterType selectedType;
  private final ChangeControl control = new ChangeControl();
  private ITemplateTypeAggregation selectedTemplate;
  private final MultiEntryMap<CharacterType, ITemplateTypeAggregation> aggregationsByType = new MultiEntryMap<CharacterType, ITemplateTypeAggregation>();
  private final CharacterStatisticsConfiguration configuration;
  private final ICharacterGenerics generics;
  private final CharacterType[] types;

  public CharacterItemCreationModel(
      ICharacterGenerics generics,
      IExaltedRuleSet preferredRuleset,
      CharacterStatisticsConfiguration configuration) {
    this.generics = generics;
    this.configuration = configuration;
    setSelectedRuleset(preferredRuleset);
    aggregateTemplates();
    this.types = collectCharacterTypes(generics.getTemplateRegistry());
  }

  private CharacterType[] collectCharacterTypes(ITemplateRegistry registry) {
    List<CharacterType> availableTypes = new ArrayList<CharacterType>();
    for (CharacterType type : CharacterType.values()) {
      if (registry.getAllSupportedTemplates(type).length > 0) {
        availableTypes.add(type);
      }
    }
    return availableTypes.toArray(new CharacterType[availableTypes.size()]);
  }

  private void aggregateTemplates() {
    TemplateTypeAggregator aggregator = new TemplateTypeAggregator(generics.getTemplateRegistry());
    for (CharacterType type : CharacterType.values()) {
      ITemplateTypeAggregation[] aggregations = aggregator.aggregateTemplates(type);
      if (aggregations.length == 0) {
        continue;
      }
      aggregationsByType.add(type, aggregations);
    }
  }

  public CharacterType[] getAvailableCharacterTypes() {
    return types;
  }

  public boolean isCharacterTypeSelected() {
    return selectedType != null;
  }

  public void setCharacterType(CharacterType type) {
    if (selectedType == type) {
      return;
    }
    this.selectedType = type;
    //TODO Set default template 
    control.fireChangedEvent();
  }

  public ITemplateTypeAggregation[] getAvailableTemplates() {
    if (selectedType == null) {
      return new ITemplateTypeAggregation[0];
    }
    return aggregationsByType.get(selectedType).toArray(new ITemplateTypeAggregation[0]);
  }

  public void setSelectedTemplate(ITemplateTypeAggregation newValue) {
    this.selectedTemplate = newValue;
    this.configuration.setTemplate(generics.getTemplateRegistry().getTemplate(
        selectedTemplate.getTemplateType(),
        configuration.getRuleSet().getEdition()));
  }

  public ITemplateTypeAggregation getSelectedTemplate() {
    return selectedTemplate;
  }

  public IExaltedRuleSet[] getAvailableRulesets() {
    return ExaltedRuleSet.values();
  }

  public IExaltedRuleSet getSelectedRuleset() {
    return this.configuration.getRuleSet();
  }

  public void setSelectedRuleset(IExaltedRuleSet newValue) {
    this.configuration.setRuleSet(newValue);
  }

  public void addListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }
}