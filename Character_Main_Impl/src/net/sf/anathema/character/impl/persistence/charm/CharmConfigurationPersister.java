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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmConfigurationPersister {

  private static final String HAZARD_RESISTANCE = "Solar.EnvironmentalHazard-ResistingMeditation"; //$NON-NLS-1$
  private static final Pattern HAZARD_RESISTANCE_PATTERN = Pattern.compile(HAZARD_RESISTANCE + "(Acid|Cold|Heat|Wind)"); //$NON-NLS-1$
  private final TextPersister textPersister = new TextPersister();

  public void save(Element parent, ICharacterStatistics statistics) {
    ICharacterTemplate template = statistics.getCharacterTemplate();
    if (!template.getMagicTemplate().getCharmTemplate().knowsCharms(statistics.getRules())) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    for (ICharacterType type : charmConfiguration.getCharacterTypes(true)) {
      ISpecialCharmPersister specialPersister = new SpecialCharmPersister(charmConfiguration.getCharmProvider()
          .getSpecialCharms(type, statistics.getRules().getEdition()), charmConfiguration.getCharmTree(type));
      saveNonMartialArtsCharmsForConfiguration(specialPersister, charmConfiguration, type, charmsElement);
    }
    saveMartialArtsCharms(charmConfiguration, charmsElement);
    saveCombos(charmsElement, statistics.getCombos());
  }

  private void saveNonMartialArtsCharmsForConfiguration(
      ISpecialCharmPersister specialPersister,
      ICharmConfiguration charmConfiguration,
      ICharacterType characterType,
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
    ICharacterType defaultCharacterType = statistics.getCharacterTemplate().getTemplateType().getCharacterType();
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      ICharacterType characterType = getCharacterTypeFromElement(groupElement, defaultCharacterType);
      ISpecialCharmPersister specialPersister;
      specialPersister = new SpecialCharmPersister(charmConfiguration.getCharmProvider().getSpecialCharms(
          characterType,
          statistics.getRules().getEdition()), charmConfiguration.getCharmTree(characterType));
      loadCharmFromConfiguration(charmConfiguration, groupElement, specialPersister);
    }
    loadCombos(charmsElement, statistics.getCombos(), charmConfiguration);
  }

  private ICharacterType getCharacterTypeFromElement(Element element, ICharacterType defaultType) {
    String typeString = element.attributeValue(ATTRIB_TYPE);
    ICharacterType characterType;
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
      boolean isOldSolarHazardResistanceCharm = isSolarHazardResistanceCharm(charmId);
      if (isOldSolarHazardResistanceCharm) {
        if (charmConfiguration.isLearned(HAZARD_RESISTANCE)) {
          continue;
        }
        charmId = HAZARD_RESISTANCE;
      }
      group.learnCharmNoParents(charmConfiguration.getCharmById(charmId), isExperienceLearned(charmElement));
      if (isOldSolarHazardResistanceCharm) {
        createHazardSpecialconfiguration(groupElement, charmConfiguration);
      }
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

  private void createHazardSpecialconfiguration(Element groupElement, ICharmConfiguration charmConfiguration) {
    IMultipleEffectCharmConfiguration configuration = (IMultipleEffectCharmConfiguration) charmConfiguration.getSpecialCharmConfiguration(HAZARD_RESISTANCE);
    for (Element charmElement : ElementUtilities.elements(groupElement, TAG_CHARM)) {
      String name = charmElement.attributeValue(ATTRIB_NAME);
      Matcher matcher = HAZARD_RESISTANCE_PATTERN.matcher(name);
      if (matcher.matches()) {
        String effectName = matcher.group(1);
        ISubeffect effect = configuration.getEffectById(effectName);
        boolean experienceLearned = isExperienceLearned(charmElement);
        effect.setCreationLearned(!experienceLearned);
        effect.setExperienceLearned(experienceLearned);
      }
    }
  }

  private boolean isSolarHazardResistanceCharm(String charmId) {
    return HAZARD_RESISTANCE_PATTERN.matcher(charmId).matches();
  }

  private void loadCombos(Element parent, IComboConfiguration comboConfiguration, ICharmConfiguration charms) {
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