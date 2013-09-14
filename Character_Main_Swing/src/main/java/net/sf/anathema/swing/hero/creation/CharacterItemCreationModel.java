package net.sf.anathema.swing.hero.creation;

import com.google.common.base.Objects;
import net.sf.anathema.character.main.HeroTemplateHolder;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private final CharacterTypes characterTypes;
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final MultiEntryMap<CharacterType, ITemplateTypeAggregation> aggregationsByType = new MultiEntryMap<>();
  private final HeroTemplateHolder templateHolder;
  private final HeroEnvironment generics;
  private final List<CharacterType> types;
  private CharacterType selectedType;
  private ITemplateTypeAggregation selectedTemplate;

  public CharacterItemCreationModel(HeroEnvironment generics, HeroTemplateHolder templateHolder) {
    this.generics = generics;
    this.templateHolder = templateHolder;
    this.characterTypes = generics.getCharacterTypes();
    this.types = collectCharacterTypes(generics.getTemplateRegistry());
    aggregateTemplates();
    setCharacterType(types.get(0));
  }

  private List<CharacterType> collectCharacterTypes(ITemplateRegistry registry) {
    List<CharacterType> availableTypes = new ArrayList<>();
    for (CharacterType type : characterTypes.findAll()) {
      if (registry.getAllSupportedTemplates(type).length > 0) {
        availableTypes.add(type);
      }
    }
    return availableTypes;
  }

  private void aggregateTemplates() {
    TemplateTypeAggregator aggregator = new TemplateTypeAggregator(generics.getTemplateRegistry());
    for (CharacterType type : characterTypes.findAll()) {
      ITemplateTypeAggregation[] aggregations = aggregator.aggregateTemplates(type);
      if (aggregations.length == 0) {
        continue;
      }
      aggregationsByType.add(type, aggregations);
    }
  }

  @Override
  public Iterable<CharacterType> getAvailableCharacterTypes() {
    return types;
  }

  @Override
  public void setCharacterType(CharacterType type) {
    if (Objects.equal(selectedType, type)) {
      return;
    }
    this.selectedType = type;
    setTemplateToDefault();
    control.announce().changeOccurred();
  }

  private void setTemplateToDefault() {
    HeroTemplate defaultTemplate = generics.getTemplateRegistry().getDefaultTemplate(selectedType);
    for (ITemplateTypeAggregation aggregation : aggregationsByType.get(selectedType)) {
      if (aggregation.contains(defaultTemplate)) {
        setSelectedTemplate(aggregation);
        return;
      }
    }
    throw new IllegalStateException("Template not contained in aggregations.");
  }

  @Override
  public ITemplateTypeAggregation[] getAvailableTemplates() {
    List<ITemplateTypeAggregation> list = aggregationsByType.get(selectedType);
    List<ITemplateTypeAggregation> copyList = new ArrayList<>(list);
    return copyList.toArray(new ITemplateTypeAggregation[copyList.size()]);
  }

  @Override
  public void setSelectedTemplate(ITemplateTypeAggregation newValue) {
    if (selectedTemplate == newValue) {
      return;
    }
    this.selectedTemplate = newValue;
    ITemplateType templateType = selectedTemplate.getTemplateType();
    HeroTemplate template = generics.getTemplateRegistry().getTemplate(templateType);
    templateHolder.setTemplate(template);
    control.announce().changeOccurred();
  }

  @Override
  public ITemplateTypeAggregation getSelectedTemplate() {
    return selectedTemplate;
  }

  @Override
  public void addListener(ChangeListener listener) {
    control.addListener(listener);
  }
}