package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.model.CombosModel;
import net.sf.anathema.hero.combos.model.CombosModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARM;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARMGROUP;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARMS;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_COMBO;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_COMBOS;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_DESCRIPTION;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_NAME;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_SPECIAL;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();
  private IMessaging messageIndicator;

  public CharmConfigurationPersister(IMessaging messageIndicator) {
    this.messageIndicator = messageIndicator;
  }

  public void save(Element parent, Hero hero) {
    ICharmTemplate charmTemplate = DefaultCharmTemplateRetriever.getNativeTemplate(hero);
    if (!charmTemplate.canLearnCharms()) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
    saveCharms(charmsElement, charmConfiguration);
    saveCombos(charmsElement, CombosModelFetcher.fetch(hero));
  }

  private CharmsModel saveCharms(Element charmsElement, CharmsModel charmConfiguration) {
    CharmSaver saver = new CharmSaver(charmConfiguration);
    for (Identifier type : charmConfiguration.getCharacterTypes(true)) {
      saver.saveCharms(type, charmsElement);
    }
    saver.saveCharms(MartialArtsUtilities.MARTIAL_ARTS, charmsElement);
    return charmConfiguration;
  }

  private ISpecialCharmPersister createSpecialCharmPersister(CharmsModel charmConfiguration) {
    return new SpecialCharmPersister(charmConfiguration.getSpecialCharms(), charmConfiguration.getCharmIdMap());
  }

  private void saveCombos(Element parent, CombosModel comboConfiguration) {
    Element combosElement = parent.addElement(TAG_COMBOS);
    for (ICombo combo : comboConfiguration.getAllCombos()) {
      Element comboElement = combosElement.addElement(TAG_COMBO);
      textPersister.saveTextualDescription(comboElement, TAG_NAME, combo.getName());
      textPersister.saveTextualDescription(comboElement, TAG_DESCRIPTION, combo.getDescription());
      for (Charm charm : combo.getCreationCharms()) {
        Element charmElement = comboElement.addElement(TAG_CHARM);
        charmElement.addAttribute(ATTRIB_NAME, charm.getId());
        charmElement.addAttribute(ATTRIB_TYPE, charm.getCharacterType().getId());
      }
      for (Charm charm : combo.getExperiencedCharms()) {
        Element charmElement = comboElement.addElement(TAG_CHARM);
        charmElement.addAttribute(ATTRIB_NAME, charm.getId());
        charmElement.addAttribute(ATTRIB_TYPE, charm.getCharacterType().getId());
        charmElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, "true");
      }
    }
  }

  public void load(Element parent, Hero hero) throws PersistenceException {
    Element charmsElement = parent.element(TAG_CHARMS);
    if (charmsElement == null) {
      return;
    }
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      loadCharmFromConfiguration(hero, charmConfiguration, groupElement, specialPersister);
    }
    loadCombos(charmsElement, CombosModelFetcher.fetch(hero), charmConfiguration);
  }

  private void loadCharmFromConfiguration(Hero hero, CharmsModel charmConfiguration, Element groupElement, ISpecialCharmPersister specialPersister) {
    String groupName = groupElement.attributeValue(ATTRIB_NAME);
    String groupType = groupElement.attributeValue(ATTRIB_TYPE);
    if (groupName.equals("Generics")) {
      groupName = "MartialArts";
    }
    ILearningCharmGroup group = loadCharmGroup(charmConfiguration, groupName, groupType);
    for (Object charmObjectElement : groupElement.elements()) {

      Element charmElement = (Element) charmObjectElement;
      String charmId = charmElement.attributeValue(ATTRIB_NAME);

      learnCharm(charmConfiguration, specialPersister, group, charmElement, charmId);
    }
  }

  private ILearningCharmGroup loadCharmGroup(CharmsModel charmConfiguration, String groupName, String groupType) {
    try {
      return charmConfiguration.getGroup(groupType, groupName);
    } catch (IllegalArgumentException e) {
      messageIndicator.addMessage("CharmPersistence.NoGroupFound", MessageType.WARNING, groupName);
      return new NullLearningCharmGroup();
    }
  }

  private void learnCharm(CharmsModel charmConfiguration, ISpecialCharmPersister specialPersister, ILearningCharmGroup group, Element charmElement,
                          String charmId) {
    try {
      Charm charm = charmConfiguration.getCharmById(charmId);
      if (!group.isLearned(charm, false)) {
        group.learnCharmNoParents(charm, isExperienceLearned(charmElement), false);
      }
      Element specialElement = charmElement.element(TAG_SPECIAL);
      CharmSpecialsModel specialConfiguration = charmConfiguration.getSpecialCharmConfiguration(charmId);
      if (specialElement != null && specialConfiguration != null) {
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      } else if (specialConfiguration instanceof MultiLearnCharmSpecials) {
        specialConfiguration.learn(isExperienceLearned(charmElement));
      }
    } catch (IllegalArgumentException e) {
      messageIndicator.addMessage("CharmPersistence.NoCharmFound", MessageType.WARNING, charmId);
    }
  }

  private boolean isExperienceLearned(Element charmElement) {
    return ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCE_LEARNED, false);
  }

  private void loadCombos(Element parent, CombosModel comboConfiguration, CharmIdMap charms) throws PersistenceException {
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
        String charmId = charmElement.attributeValue(ATTRIB_NAME);
        try {
          Charm comboCharm = charms.getCharmById(charmId);
          comboConfiguration.addCharmToCombo(comboCharm, charmExperiencedLearned);
        } catch (IllegalArgumentException e) {
          messageIndicator.addMessage("CharmPersistence.NoCharmFound", MessageType.WARNING, charmId);
        }
      }
      comboConfiguration.finalizeCombo();
    }
  }

}