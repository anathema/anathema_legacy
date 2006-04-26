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
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.trait.persistence.AbstractCharacterPersister;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmConfigurationPersister extends AbstractCharacterPersister {

  public void save(Element parent, ICharacterStatistics statistics) {
    ICharacterTemplate template = statistics.getCharacterTemplate();
    if (!template.getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    for (CharacterType type : charmConfiguration.getCharacterTypes(true)) {
      ISpecialCharmPersister specialPersister = new SpecialCharmPersister(charmConfiguration.getCharmProvider()
          .getSpecialCharms(type), charmConfiguration.getCharmTree(type));
      saveNonMartialArtsCharmsForConfiguration(specialPersister, charmConfiguration, type, charmsElement);
    }
    saveMartialArtsCharms(charmConfiguration, charmsElement);
    saveCombos(charmsElement, statistics.getCombos());
  }

  private void saveNonMartialArtsCharmsForConfiguration(
      ISpecialCharmPersister specialPersister,
      ICharmConfiguration charmConfiguration,
      CharacterType characterType,
      Element charmsElement) {
    for (ILearningCharmGroup group : charmConfiguration.getNonMartialArtsGroups(characterType)) {
      saveCharmGroup(charmsElement, group, specialPersister);
    }
  }

  private void saveMartialArtsCharms(ICharmConfiguration charmConfiguration, Element charmsElement) {
    for (ILearningCharmGroup group : charmConfiguration.getMartialArtsGroups()) {
      saveCharmGroup(charmsElement, group, null);
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
      saveTextualDescription(comboElement, TAG_NAME, combo.getName());
      saveTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
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
    CharacterType defaultCharacterType = statistics.getCharacterTemplate().getTemplateType().getCharacterType();
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      CharacterType characterType = getCharacterTypeFromElement(groupElement, defaultCharacterType);
      if (characterType == null) {
        characterType = statistics.getCharacterTemplate().getTemplateType().getCharacterType();
      }
      ISpecialCharmPersister specialPersister;
      specialPersister = new SpecialCharmPersister(charmConfiguration.getCharmProvider()
          .getSpecialCharms(characterType), charmConfiguration.getCharmTree(characterType));
      loadCharmFromConfiguration(charmConfiguration, groupElement, specialPersister);
    }
    loadCombos(charmsElement, statistics.getCombos(), charmConfiguration);
  }

  private CharacterType getCharacterTypeFromElement(Element element, CharacterType defaultType) {
    String typeString = element.attributeValue(ATTRIB_TYPE);
    CharacterType characterType;
    if (typeString == null) {
      characterType = defaultType;
    }
    else {
      characterType = CharacterType.getById(typeString);
    }
    return characterType;
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
      boolean experienceLearned = ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCE_LEARNED, false);
      group.learnCharmNoParents(charmConfiguration.getCharmById(charmId), experienceLearned);
      Element specialElement = charmElement.element(TAG_SPECIAL);
      if (specialElement != null) {
        ISpecialCharmConfiguration specialConfiguration = charmConfiguration.getSpecialCharmConfiguration(charmId);
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      }
    }
  }

  private void loadCombos(Element parent, IComboConfiguration comboConfiguration, ICharmConfiguration charms) {
    Element combosElement = parent.element(TAG_COMBOS);
    if (combosElement == null) {
      return;
    }
    for (Object comboElementObject : combosElement.elements(TAG_COMBO)) {
      Element comboElement = (Element) comboElementObject;
      ICombo combo = comboConfiguration.getEditCombo();
      boolean experienceLearned = ElementUtilities.getBooleanAttribute(comboElement, ATTRIB_EXPERIENCE_LEARNED, false);
      restoreTextualDescription(comboElement, TAG_NAME, combo.getName());
      restoreTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (Object charmElementObject : comboElement.elements(TAG_CHARM)) {
        Element charmElement = (Element) charmElementObject;
        ICharm comboCharm = charms.getCharmById(charmElement.attributeValue(ATTRIB_NAME));
        comboConfiguration.addCharmToCombo(comboCharm);
      }
      comboConfiguration.finalizeCombo(experienceLearned);
    }
  }
}