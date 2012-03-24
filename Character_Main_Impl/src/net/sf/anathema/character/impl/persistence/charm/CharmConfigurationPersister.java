package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
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
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.*;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();
  private final TraitPersister traitPersister = new TraitPersister();
  private static final String TAG_LEARN_COUNT = "LearnCount";
  private static final String ATTRIB_CREATION_LEARNED = "creationlearned";
  private static final String ATTRIB_ID = "id";
  private static final String TAG_SUBEFFECTS = "Subeffects";
  private static final String TAG_SUBEFFECT = "Subeffect";
  private ITraitContext context;

  public void save(Element parent, ICharacterStatistics statistics) {
    ICharacterTemplate template = statistics.getCharacterTemplate();
    ICharmTemplate charmTemplate = template.getMagicTemplate().getCharmTemplate();
    if (!charmTemplate.canLearnCharms()) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    saveCharms(charmsElement, charmTemplate, charmConfiguration);
    saveCombos(charmsElement, statistics.getCombos());
    saveFilters(charmsElement, charmConfiguration.getCharmFilters());
  }

  private ICharmConfiguration saveCharms(Element charmsElement, ICharmTemplate charmTemplate, ICharmConfiguration charmConfiguration) {
    CharmSaver saver = new CharmSaver(charmConfiguration);
    for (IIdentificate type : charmConfiguration.getCharacterTypes(true)) {
      saver.saveCharms(type, charmsElement);
    }
    if (charmTemplate.hasUniqueCharms()) {
      saver.saveCharms(charmTemplate.getUniqueCharmType().getId(), charmsElement);
    }
    saver.saveCharms(MartialArtsUtilities.MARTIAL_ARTS, charmsElement);
    return charmConfiguration;
  }

  private void saveFilters(Element charmsElement, List<ICharmFilter> charmFilters) {
    Element filtersElement = charmsElement.addElement(TAG_CHARMFILTERS);
    for (ICharmFilter filter : charmFilters)
      filter.save(filtersElement);
  }

  private ISpecialCharmPersister createSpecialCharmPersister(ICharmConfiguration charmConfiguration) {
    return new SpecialCharmPersister(charmConfiguration.getSpecialCharms(), charmConfiguration.getCharmIdMap());
  }

  private void saveCombos(Element parent, IComboConfiguration comboConfiguration) {
    Element combosElement = parent.addElement(TAG_COMBOS);
    for (ICombo combo : comboConfiguration.getAllCombos()) {
      Element comboElement = combosElement.addElement(TAG_COMBO);
      textPersister.saveTextualDescription(comboElement, TAG_NAME, combo.getName());
      textPersister.saveTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (ICharm charm : combo.getCreationCharms()) {
        Element charmElement = comboElement.addElement(TAG_CHARM);
        charmElement.addAttribute(ATTRIB_NAME, charm.getId());
        charmElement.addAttribute(ATTRIB_TYPE, charm.getCharacterType().getId());
      }
      for (ICharm charm : combo.getExperiencedCharms()) {
        Element charmElement = comboElement.addElement(TAG_CHARM);
        charmElement.addAttribute(ATTRIB_NAME, charm.getId());
        charmElement.addAttribute(ATTRIB_TYPE, charm.getCharacterType().getId());
        charmElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, "true");
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

  private void loadCharmFromConfiguration(ICharmConfiguration charmConfiguration, Element groupElement,
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
      if (specialElement != null && specialConfiguration != null) {
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      } else if (specialConfiguration instanceof IMultiLearnableCharmConfiguration) {

      }
    }
  }

  private String parseTrueName(Element element, String name) {
    StringBuilder baseCharmName = new StringBuilder();
    String[] components = name.split("\\.");
    if (components.length > 3) {
      for (int i = 0; i != components.length - 2; i++) {
        baseCharmName.append(components[i]);
        baseCharmName.append(i == components.length - 3 ? "" : ".");
      }
      if (components[components.length - 1].startsWith("Repurchase")) {
    	  int count = Integer.parseInt(components[components.length - 1].replace("Repurchase", ""));
          Element newElement = element.addElement(TAG_SPECIAL);
          DefaultTrait trait = new LimitedTrait(new TraitType(TAG_LEARN_COUNT), SimpleTraitTemplate.createEssenceLimitedTemplate(0, count, LowerableState.Default),
                                                null, context);
          traitPersister.saveTrait(newElement, TAG_LEARN_COUNT, trait);  
      }
      if (components[components.length - 2].startsWith("Subeffect")) {
    	  Element newElement = element.addElement(TAG_SPECIAL).addElement(TAG_SUBEFFECTS).addElement(TAG_SUBEFFECT);
    	  boolean creationLearned = !ElementUtilities.getBooleanAttribute(element, ATTRIB_EXPERIENCE_LEARNED, true);
    	  newElement.addAttribute(ATTRIB_ID, components[components.length - 1]);
    	  newElement.addAttribute(ATTRIB_CREATION_LEARNED, "" + creationLearned);
    	  newElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, "" + true);
      }
    } else {
      baseCharmName.append(name);
    }
    return baseCharmName.toString();
  }

  private boolean isExperienceLearned(Element charmElement) {
    return ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCE_LEARNED, false);
  }

  private void loadCombos(Element parent, IComboConfiguration comboConfiguration, ICharmIdMap charms) throws PersistenceException {
    Element combosElement = parent.element(TAG_COMBOS);
    if (combosElement == null) {
      return;
    }
    for (Object comboElementObject : combosElement.elements(TAG_COMBO)) {
      Element comboElement = (Element) comboElementObject;
      ICombo combo = comboConfiguration.getEditCombo();
      textPersister.restoreTextualDescription(comboElement, TAG_NAME, combo.getName());
      textPersister.restoreTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (Object charmElementObject : comboElement.elements(TAG_CHARM)) {
        Element charmElement = (Element) charmElementObject;
        boolean charmExperiencedLearned = isExperienceLearned(charmElement);
        ICharm comboCharm = charms.getCharmById(charmElement.attributeValue(ATTRIB_NAME));
        comboConfiguration.addCharmToCombo(comboCharm, charmExperiencedLearned);
      }
      comboConfiguration.finalizeCombo();
    }
  }

  private void loadFilters(Element parent, ICharacterStatistics statistics) {
    ICharmConfiguration configuration = statistics.getCharms();
    Element charmFilterNode = parent.element(TAG_CHARMFILTERS);
    if (charmFilterNode != null) {
      for (Object filterNode : charmFilterNode.elements()) {
        Element node = (Element) filterNode;
        for (ICharmFilter filter : configuration.getCharmFilters()) {
          if (filter.load(node)) break;
        }
      }
    }
  }
}