package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARMGROUP;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARMS;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_SPECIAL;

public class CharmConfigurationPersister {

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

  public void load(Element parent, Hero hero) throws PersistenceException {
    Element charmsElement = parent.element(TAG_CHARMS);
    if (charmsElement == null) {
      return;
    }
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
    ISpecialCharmPersister specialPersister = createSpecialCharmPersister(charmConfiguration);
    for (Object groupObjectElement : charmsElement.elements(TAG_CHARMGROUP)) {
      Element groupElement = (Element) groupObjectElement;
      loadCharmFromConfiguration(charmConfiguration, groupElement, specialPersister);
    }
  }

  private void loadCharmFromConfiguration(CharmsModel charmConfiguration, Element groupElement, ISpecialCharmPersister specialPersister) {
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
}