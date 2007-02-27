package net.sf.anathema.character.impl.module.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private ICharacterType selectedType;
  private final ChangeControl control = new ChangeControl();
  private ITemplateTypeAggregation selectedTemplate;
  private final MultiEntryMap<ICharacterType, ITemplateTypeAggregation> aggregationsByType = new MultiEntryMap<ICharacterType, ITemplateTypeAggregation>();
  private final CharacterStatisticsConfiguration configuration;
  private final ICharacterGenerics generics;
  private final ICharacterType[] types;

  public CharacterItemCreationModel(
      ICharacterGenerics generics,
      IExaltedRuleSet preferredRuleset,
      CharacterStatisticsConfiguration configuration) {
    this.generics = generics;
    this.configuration = configuration;
    setSelectedRuleset(preferredRuleset);
    aggregateTemplates();
    this.types = collectCharacterTypes(generics.getTemplateRegistry());
    setCharacterType(CharacterType.SOLAR);
  }

  private ICharacterType[] collectCharacterTypes(ITemplateRegistry registry) {
    List<ICharacterType> availableTypes = new ArrayList<ICharacterType>();
    for (ICharacterType type : CharacterType.values()) {
      if (registry.getAllSupportedTemplates(type).length > 0) {
        availableTypes.add(type);
      }
    }
    return availableTypes.toArray(new CharacterType[availableTypes.size()]);
  }

  private void aggregateTemplates() {
    TemplateTypeAggregator aggregator = new TemplateTypeAggregator(generics.getTemplateRegistry());
    for (ICharacterType type : CharacterType.values()) {
      ITemplateTypeAggregation[] aggregations = aggregator.aggregateTemplates(type);
      if (aggregations.length == 0) {
        continue;
      }
      aggregationsByType.add(type, aggregations);
    }
  }

  public boolean isSelectionComplete() {
    return configuration.getTemplate() != null && configuration.getRuleSet() != null;
  }

  public boolean isCharacterTypeSelected() {
    return selectedType != null;
  }

  public ICharacterType[] getAvailableCharacterTypes() {
    return types;
  }

  public void setCharacterType(ICharacterType type) {
    if (selectedType == type) {
      return;
    }
    this.selectedType = type;
    setTemplateToDefault();
    control.fireChangedEvent();
  }

  private void setTemplateToDefault() {
    if (getAvailableTemplates().length == 0) {
      setSelectedTemplate(null);
    }
    else {
      ICharacterTemplate defaultTemplate = generics.getTemplateRegistry().getDefaultTemplate(
          selectedType,
          configuration.getRuleSet().getEdition());
      for (ITemplateTypeAggregation aggregation : aggregationsByType.get(selectedType)) {
        if (aggregation.contains(defaultTemplate)) {
          setSelectedTemplate(aggregation);
          return;
        }
      }
      throw new IllegalStateException("Template not contained in aggregations."); //$NON-NLS-1$
    }
  }

  public ITemplateTypeAggregation[] getAvailableTemplates() {
    List<ITemplateTypeAggregation> list = aggregationsByType.get(selectedType);
    List<ITemplateTypeAggregation> copyList = new ArrayList<ITemplateTypeAggregation>(list);
    for (ITemplateTypeAggregation aggregation : list) {
      if (!aggregation.supportsEdition(configuration.getRuleSet().getEdition())) {
        copyList.remove(aggregation);
      }
    }
    return copyList.toArray(new ITemplateTypeAggregation[copyList.size()]);
  }

  public void setSelectedTemplate(ITemplateTypeAggregation newValue) {
    if (selectedTemplate == newValue) {
      return;
    }
    this.selectedTemplate = newValue;
    if (selectedTemplate == null) {
      configuration.setTemplate(null);
    }
    else {
      setEditionDependentTemplate();
    }
    control.fireChangedEvent();
  }

  private void setEditionDependentTemplate() {
    configuration.setTemplate(generics.getTemplateRegistry().getTemplate(
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
    if (configuration.getRuleSet() == newValue) {
      return;
    }
    configuration.setRuleSet(newValue);
    if (selectedTemplate != null && selectedTemplate.supportsEdition(newValue.getEdition())) {
      setEditionDependentTemplate();
    }
    else {
      setTemplateToDefault();
    }
    control.fireChangedEvent();
  }

  public void addListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }
}