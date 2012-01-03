package net.sf.anathema.character.impl.persistence.charm;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARM;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMGROUP;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMS;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_COMBO;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_COMBOS;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIAL;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMFILTERS;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.presenter.charm.ObtainableCharmFilter;
import net.sf.anathema.character.presenter.charm.SourceBookCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();
  private final TraitPersister traitPersister = new TraitPersister();
  private final String TAG_LEARN_COUNT = "LearnCount";
  private ITraitContext context;

  public void save(Element parent, ICharacterStatistics statistics) {
    ICharacterTemplate template = statistics.getCharacterTemplate();
    if (!template.getMagicTemplate().getCharmTemplate().canLearnCharms(statistics.getRules())) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (IIdentificate type : charmConfiguration.getCharacterTypes(true)) {
      saveCharms(specialPersister, charmConfiguration, type, charmsElement);
    }
    saveCharms(specialPersister, charmConfiguration, MartialArtsUtilities.MARTIAL_ARTS, charmsElement);
    saveCombos(charmsElement, statistics.getCombos());
    saveFilters(charmsElement, charmConfiguration.getCharmFilters());
  }

  private void saveFilters(Element charmsElement, List<ICharmFilter> charmFilters)
  {
	  Element filtersElement = charmsElement.addElement(TAG_CHARMFILTERS);
	  
	  for (ICharmFilter filter : charmFilters)
		  filter.save(filtersElement);
  }

private ISpecialCharmPersister createSpecialCharmPersister(ICharmConfiguration charmConfiguration) {
    return new SpecialCharmPersister(charmConfiguration.getSpecialCharms(), charmConfiguration.getCharmIdMap());
  }

  private void saveCharms(
      ISpecialCharmPersister specialPersister,
      ICharmConfiguration charmConfiguration,
      IIdentificate type,
      Element charmsElement) {
    for (ILearningCharmGroup group : charmConfiguration.getCharmGroups(type)) {
      if (group.hasLearnedCharms()) {
        saveCharmGroup(charmsElement, group, specialPersister, charmConfiguration);
      }
    }
  }

  private void saveCharmGroup(
      Element charmsElement,
      ILearningCharmGroup group,
      ISpecialCharmPersister specialPersister,
      ICharmConfiguration charmConfiguration) {
    Element groupElement = charmsElement.addElement(TAG_CHARMGROUP);
    groupElement.addAttribute(ATTRIB_NAME, group.getId());
    groupElement.addAttribute(ATTRIB_TYPE, group.getCharacterType().getId());
    for (ICharm charm : group.getCreationLearnedCharms()) {
      saveCharm(charmConfiguration, specialPersister, groupElement, charm, false);
    }
    for (ICharm charm : group.getExperienceLearnedCharms()) {
      saveCharm(charmConfiguration, specialPersister, groupElement, charm, true);
    }
  }

  private void saveCharm(
      ICharmConfiguration charmConfiguration,
      ISpecialCharmPersister specialPersister,
      Element groupElement,
      ICharm charm,
      boolean experienceLearned) {
    Element charmElement = groupElement.addElement(TAG_CHARM);
    charmElement.addAttribute(ATTRIB_NAME, charm.getId());
    charmElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, String.valueOf(experienceLearned));
    ISpecialCharmConfiguration specialCharmConfiguration = charmConfiguration.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      Element specialElement = charmElement.addElement(TAG_SPECIAL);
      specialPersister.saveConfiguration(specialElement, specialCharmConfiguration);
    }
  }

  private void saveCombos(Element parent, IComboConfiguration comboConfiguration) {
    Element combosElement = parent.addElement(TAG_COMBOS);
    for (ICombo combo : comboConfiguration.getCurrentCombos()) {
      Element comboElement = combosElement.addElement(TAG_COMBO);
      comboElement.addAttribute(
          ATTRIB_EXPERIENCE_LEARNED,
          String.valueOf(!comboConfiguration.isLearnedOnCreation(combo)));
      textPersister.saveTextualDescription(comboElement, TAG_NAME, combo.getName());
      textPersister.saveTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (ICharm charm : combo.getCharms()) {
        Element charmElement = comboElement.addElement(TAG_CHARM);
        charmElement.addAttribute(ATTRIB_NAME, charm.getId());
        charmElement.addAttribute(ATTRIB_TYPE, charm.getCharacterType().getId());
      }
    }
  }

  public void load(Element parent, ICharacterStatistics statistics) throws PersistenceException {
    Element charmsElement = parent.element(TAG_CHARMS);
    if (charmsElement == null) {
      return;
    }
    context = statistics.getCharacterContext().getTraitContext();
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      loadCharmFromConfiguration(charmConfiguration, groupElement, specialPersister);
    }
    loadCombos(charmsElement, statistics.getCombos(), charmConfiguration);
    loadFilters(charmsElement, statistics);
  }

  private void loadCharmFromConfiguration(
      ICharmConfiguration charmConfiguration,
      Element groupElement,
      ISpecialCharmPersister specialPersister) throws PersistenceException {
    String groupName = groupElement.attributeValue(ATTRIB_NAME);
    String groupType = groupElement.attributeValue(ATTRIB_TYPE);
    if (groupName.equals("Generics")) { //$NON-NLS-1$
      groupName = "MartialArts"; //$NON-NLS-1$
    }
    ILearningCharmGroup group = charmConfiguration.getGroup(groupType, groupName);
    for (Object charmObjectElement : groupElement.elements()) {
      Element charmElement = (Element) charmObjectElement;
      String charmId = charmElement.attributeValue(ATTRIB_NAME);
      String charmTrueName = charmConfiguration.getCharmTrueName(charmId);
      charmId = parseTrueName(charmElement, charmTrueName);
      group.learnCharmNoParents(charmConfiguration.getCharmById(charmId), isExperienceLearned(charmElement), false);
      Element specialElement = charmElement.element(TAG_SPECIAL);
      ISpecialCharmConfiguration specialConfiguration = charmConfiguration.getSpecialCharmConfiguration(charmId);
      if (specialElement != null) {
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      }
      else if (specialConfiguration instanceof IMultiLearnableCharmConfiguration) {
        
      }
    }
  }
  
  private String parseTrueName(Element element, String name)
  {
	  StringBuilder baseCharmName = new StringBuilder();
	  String[] components = name.split("\\.");
	  if (components.length > 3)
	  {
		  for (int i = 0; i != components.length - 2; i++) {
		    baseCharmName.append(components[i]);
		    baseCharmName.append(i == components.length - 3 ? "" : ".");
		  }
		  int count = Integer.parseInt(components[components.length - 1].replace("Repurchase", ""));
		  Element newElement = element.addElement(TAG_SPECIAL);
		  DefaultTrait trait = new LimitedTrait(new TraitType(TAG_LEARN_COUNT),
				  SimpleTraitTemplate.createEssenceLimitedTemplate(0, count, LowerableState.Default),
				  null,
				  context);
		  traitPersister.saveTrait(newElement, TAG_LEARN_COUNT, trait);
	  }
	  else {
	    baseCharmName.append(name);
	  }
	  return baseCharmName.toString();
  }

  private boolean isExperienceLearned(Element charmElement) {
    return ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCE_LEARNED, false);
  }

  private void loadCombos(Element parent, IComboConfiguration comboConfiguration, ICharmIdMap charms) {
    Element combosElement = parent.element(TAG_COMBOS);
    if (combosElement == null) {
      return;
    }
    for (Object comboElementObject : combosElement.elements(TAG_COMBO)) {
      Element comboElement = (Element) comboElementObject;
      ICombo combo = comboConfiguration.getEditCombo();
      boolean experienceLearned = isExperienceLearned(comboElement);
      textPersister.restoreTextualDescription(comboElement, TAG_NAME, combo.getName());
      textPersister.restoreTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (Object charmElementObject : comboElement.elements(TAG_CHARM)) {
        Element charmElement = (Element) charmElementObject;
        ICharm comboCharm = charms.getCharmById(charmElement.attributeValue(ATTRIB_NAME));
        comboConfiguration.addCharmToCombo(comboCharm);
      }
      comboConfiguration.finalizeCombo(experienceLearned);
    }
  }
  
  private void loadFilters(Element parent, ICharacterStatistics statistics)
  {
	  ICharmConfiguration config = statistics.getCharms();
	  List<ICharmFilter> filterSet = new ArrayList<ICharmFilter>();
	  filterSet.add(new ObtainableCharmFilter(config));
	  filterSet.add(new SourceBookCharmFilter(statistics.getRules().getEdition(),
			  config));
	  
	  Element charmFilterNode = parent.element(TAG_CHARMFILTERS);
	  if (charmFilterNode != null)
	  {
		  for (Object filterNode : charmFilterNode.elements())
		  {
			  Element node = (Element) filterNode;
			  for (ICharmFilter filter : filterSet)
			  {
				  if (filter.load(node))
					  break;
			  }
		  }
	  }
	  
	  config.setCharmFilters(filterSet);
  }
}