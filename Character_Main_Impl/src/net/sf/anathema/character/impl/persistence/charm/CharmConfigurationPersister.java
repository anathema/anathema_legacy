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
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();

  public void save(Element parent, ICharacterStatistics statistics) {
    ICharacterTemplate template = statistics.getCharacterTemplate();
    if (!template.getMagicTemplate().getCharmTemplate().knowsCharms(statistics.getRules())) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (IIdentificate type : charmConfiguration.getCharacterTypes(true)) {
      saveTypeCharms(specialPersister, charmConfiguration, type, charmsElement);
    }
    saveTypeCharms(specialPersister, charmConfiguration, MartialArtsUtilities.MARTIAL_ARTS, charmsElement);
    saveCombos(charmsElement, statistics.getCombos());
  }

  private ISpecialCharmPersister createSpecialCharmPersister(ICharmConfiguration charmConfiguration) {
    ISpecialCharmPersister specialPersister = new SpecialCharmPersister(
        charmConfiguration.getSpecialCharms(),
        charmConfiguration.getCharmIdMap());
    return specialPersister;
  }

  private void saveTypeCharms(
      ISpecialCharmPersister specialPersister,
      ICharmConfiguration charmConfiguration,
      IIdentificate type,
      Element charmsElement) {
    for (ILearningCharmGroup group : charmConfiguration.getCharmGroups(type)) {
      saveCharmGroup(charmsElement, group, specialPersister);
    }
  }

  private void saveCharmGroup(Element charmsElement, ILearningCharmGroup group, ISpecialCharmPersister specialPersister) {
    ICharm[] creationLearnedCharms = group.getCreationLearnedCharms();
    ICharm[] experienceLearnedCharms = group.getExperienceLearnedCharms();
    if (creationLearnedCharms.length + experienceLearnedCharms.length > 0) {
      Element groupElement = charmsElement.addElement(TAG_CHARMGROUP);
      groupElement.addAttribute(ATTRIB_NAME, group.getId());
      groupElement.addAttribute(ATTRIB_TYPE, group.getCharacterType().getId());
      for (ICharm charm : creationLearnedCharms) {
        saveCharm(group, specialPersister, groupElement, charm, false);
      }
      for (ICharm charm : experienceLearnedCharms) {
        saveCharm(group, specialPersister, groupElement, charm, true);
      }
    }
  }

  private void saveCharm(
      ILearningCharmGroup group,
      ISpecialCharmPersister specialPersister,
      Element groupElement,
      ICharm charm,
      boolean experienceLearned) {
    Element charmElement = groupElement.addElement(TAG_CHARM);
    charmElement.addAttribute(ATTRIB_NAME, charm.getId());
    charmElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, String.valueOf(experienceLearned));
    ISpecialCharmConfiguration specialCharmConfiguration = group.getSpecialCharmConfiguration(charm);
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
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      loadCharmFromConfiguration(charmConfiguration, groupElement, specialPersister);
    }
    loadCombos(charmsElement, statistics.getCombos(), charmConfiguration);
  }

  private void loadCharmFromConfiguration(
      ICharmConfiguration charmConfiguration,
      Element groupElement,
      ISpecialCharmPersister specialPersister) throws PersistenceException {
    String groupName = groupElement.attributeValue(ATTRIB_NAME);
    String groupType = groupElement.attributeValue(ATTRIB_TYPE);
    ILearningCharmGroup group = charmConfiguration.getGroup(groupType, groupName);
    for (Object charmObjectElement : groupElement.elements()) {
      Element charmElement = (Element) charmObjectElement;
      String charmId = charmElement.attributeValue(ATTRIB_NAME);
      group.learnCharmNoParents(charmConfiguration.getCharmById(charmId), isExperienceLearned(charmElement));
      Element specialElement = charmElement.element(TAG_SPECIAL);
      if (specialElement != null) {
        ISpecialCharmConfiguration specialConfiguration = charmConfiguration.getSpecialCharmConfiguration(charmId);
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      }
    }
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
}