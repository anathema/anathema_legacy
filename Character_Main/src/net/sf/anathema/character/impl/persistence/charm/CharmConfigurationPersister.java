package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.DefaultTraitType;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.combos.CombosModel;
import net.sf.anathema.character.main.model.combos.CombosModelFetcher;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.charmtree.filters.CharmFilter;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createEssenceLimitedTemplate;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARM;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMFILTERS;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMGROUP;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMS;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_COMBO;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_COMBOS;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_DESCRIPTION;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIAL;

public class CharmConfigurationPersister {

  private final TextPersister textPersister = new TextPersister();
  private final TraitPersister traitPersister = new TraitPersister();
  private static final String TAG_LEARN_COUNT = "LearnCount";
  private static final String ATTRIB_CREATION_LEARNED = "creationlearned";
  private static final String ATTRIB_ID = "id";
  private static final String TAG_SUBEFFECTS = "Subeffects";
  private static final String TAG_SUBEFFECT = "Subeffect";
  private IMessaging messageIndicator;

  public CharmConfigurationPersister(IMessaging messageIndicator) {
    this.messageIndicator = messageIndicator;
  }

  public void save(Element parent, Hero hero) {
    HeroTemplate template = hero.getTemplate();
    ICharmTemplate charmTemplate = template.getMagicTemplate().getCharmTemplate();
    if (!charmTemplate.canLearnCharms()) {
      return;
    }
    Element charmsElement = parent.addElement(TAG_CHARMS);
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
    saveCharms(charmsElement, charmConfiguration);
    saveCombos(charmsElement, CombosModelFetcher.fetch(hero));
    saveFilters(charmsElement, charmConfiguration.getCharmFilters());
  }

  private CharmsModel saveCharms(Element charmsElement, CharmsModel charmConfiguration) {
    CharmSaver saver = new CharmSaver(charmConfiguration);
    for (Identifier type : charmConfiguration.getCharacterTypes(true)) {
      saver.saveCharms(type, charmsElement);
    }
    saver.saveCharms(MartialArtsUtilities.MARTIAL_ARTS, charmsElement);
    return charmConfiguration;
  }

  private void saveFilters(Element charmsElement, List<CharmFilter> charmFilters) {
    Element filtersElement = charmsElement.addElement(TAG_CHARMFILTERS);
    for (CharmFilter filter : charmFilters) {
      filter.save(filtersElement);
    }
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
    loadFilters(charmsElement, hero);
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
      String charmTrueName = charmConfiguration.getCharmTrueName(charmId);

      charmId = parseTrueName(hero, charmElement, charmTrueName, charmConfiguration, isExperienceLearned(charmElement));
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
      ICharm charm = charmConfiguration.getCharmById(charmId);
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
    StringBuilder baseCharmName = new StringBuilder();
    String[] components = name.split("\\.");
    if (components.length > 3) {
      for (int i = 0; i != components.length - 2; i++) {
        baseCharmName.append(components[i]);
        baseCharmName.append(i == components.length - 3 ? "" : ".");
      }
      if (components[components.length - 1].startsWith("Repurchase")) {
        int count = Integer.parseInt(components[components.length - 1].replace("Repurchase", ""));
        Element specialCharmElement = element.addElement(TAG_SPECIAL);
        DefaultTrait trait =
                new LimitedTrait(hero, new DefaultTraitType(TAG_LEARN_COUNT), createEssenceLimitedTemplate(0, 0, LowerableState.Default), null);
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
        traitPersister.saveTrait(specialCharmElement, TAG_LEARN_COUNT, trait);
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

  private void loadCombos(Element parent, CombosModel comboConfiguration, ICharmIdMap charms) throws PersistenceException {
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
          ICharm comboCharm = charms.getCharmById(charmId);
          comboConfiguration.addCharmToCombo(comboCharm, charmExperiencedLearned);
        } catch (IllegalArgumentException e) {
          messageIndicator.addMessage("CharmPersistence.NoCharmFound", MessageType.WARNING, charmId);
        }
      }
      comboConfiguration.finalizeCombo();
    }
  }

  private void loadFilters(Element parent, Hero hero) {
    CharmsModel configuration = CharmsModelFetcher.fetch(hero);
    Element charmFilterNode = parent.element(TAG_CHARMFILTERS);
    if (charmFilterNode != null) {
      for (Object filterNode : charmFilterNode.elements()) {
        Element node = (Element) filterNode;
        for (CharmFilter filter : configuration.getCharmFilters()) {
          if (filter.load(node)) {
            break;
          }
        }
      }
    }
  }
}