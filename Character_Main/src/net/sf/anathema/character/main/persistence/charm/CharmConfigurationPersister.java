package net.sf.anathema.character.main.persistence.charm;

import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.DefaultTraitType;
import net.sf.anathema.character.main.library.trait.LimitedTrait;
import net.sf.anathema.character.main.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.traits.LowerableState;
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
import static net.sf.anathema.character.main.traits.SimpleTraitTemplate.createEssenceLimitedTemplate;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();
  private final TraitPersister traitPersister = new TraitPersister();
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

      charmId = parseTrueName(hero, charmElement, charmId, charmConfiguration, isExperienceLearned(charmElement));
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
      ISpecialCharmConfiguration specialConfiguration = charmConfiguration.getSpecialCharmConfiguration(charmId);
      if (specialElement != null && specialConfiguration != null) {
        specialPersister.loadConfiguration(specialElement, specialConfiguration);
      } else if (specialConfiguration instanceof IMultiLearnableCharmConfiguration) {
        specialConfiguration.learn(isExperienceLearned(charmElement));
      }
    } catch (IllegalArgumentException e) {
      messageIndicator.addMessage("CharmPersistence.NoCharmFound", MessageType.WARNING, charmId);
    }
  }

  private String parseTrueName(Hero hero, Element element, String name, CharmsModel config, boolean isExperienceLearned) {
    String[] components = name.split("\\.");
    if (components.length <= 3) {
      return name;
    } else {
      StringBuilder baseCharmName = new StringBuilder();
      for (int i = 0; i != components.length - 2; i++) {
        baseCharmName.append(components[i]);
        baseCharmName.append(i == components.length - 3 ? "" : ".");
      }
      boolean isRepurchaseInfo = components[components.length - 1].startsWith("Repurchase");
      //This adapts the Charm from an old format to repurchase configurations, modifying only the in-memory representation.
      //TODO (Sandra) still wants to understand this
      if (isRepurchaseInfo) {
        int count = Integer.parseInt(components[components.length - 1].replace("Repurchase", ""));
        Element specialCharmElement = element.addElement(TAG_SPECIAL);
        DefaultTrait trait =
                new LimitedTrait(hero, new DefaultTraitType(MultiLearnCharmPersister.TAG_LEARN_COUNT), createEssenceLimitedTemplate(0, 0, LowerableState.Default), null);
        ISpecialCharmConfiguration specialConfiguration = config.getSpecialCharmConfiguration(baseCharmName.toString());
        if (specialConfiguration instanceof IMultiLearnableCharmConfiguration) {
          IMultiLearnableCharmConfiguration multiConfig = (IMultiLearnableCharmConfiguration) specialConfiguration;
          trait.setUncheckedCreationValue(multiConfig.getCreationLearnCount());
          trait.setExperiencedValue(multiConfig.getCurrentLearnCount());
        }
        if (count > trait.getCurrentValue()) {
          if (isExperienceLearned) {
            trait.setUncheckedExperiencedValue(count);
          } else {
            trait.setUncheckedCreationValue(count);
          }
        }
        traitPersister.saveTrait(specialCharmElement, MultiLearnCharmPersister.TAG_LEARN_COUNT, trait);
      }
      //This adapts the Charm from an old format to subeffect configurations, modifying only the in-memory representation.
      //TODO (Sandra) still wants to understand this
      boolean isSubeffectCharm = components[components.length - 2].startsWith("Subeffect");
      if (isSubeffectCharm) {
        Element newElement = element.addElement(TAG_SPECIAL).addElement(MultipleEffectCharmPersister.TAG_SUBEFFECTS).addElement(MultipleEffectCharmPersister.TAG_SUBEFFECT);
        boolean creationLearned = !ElementUtilities.getBooleanAttribute(element, ATTRIB_EXPERIENCE_LEARNED, true);
        newElement.addAttribute(MultipleEffectCharmPersister.ATTRIB_ID, components[components.length - 1]);
        newElement.addAttribute(MultipleEffectCharmPersister.ATTRIB_CREATION_LEARNED, "" + creationLearned);
        newElement.addAttribute(MultipleEffectCharmPersister.ATTRIB_EXPERIENCE_LEARNED, "" + true);
      }
      return baseCharmName.toString();
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