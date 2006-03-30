package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_SUB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_STATISTICS;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.persistence.charm.CharmConfigurationPersister;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.nature.INatureProvider;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharacterStatisticPersister {

  // Abwärtskompatibilität
  private static final IIdentificate OLD_HEROIC_MORTAL_TYPE_ID = new Identificate("HeroicMortal"); //$NON-NLS-1$

  private final AttributeConfigurationPersister attributePersister = new AttributeConfigurationPersister();
  private final AbilityConfigurationPersister abilityPersister = new AbilityConfigurationPersister();
  private final CharacterConceptPersister characterConceptPersister;
  private final EssenceConfigurationPersister essencePersister = new EssenceConfigurationPersister();
  private final VirtueConfigurationPersister virtuePersister;
  private final BackgroundConfigurationPersister backgroundPersister;
  private final WillpowerConfigurationPersister willpowerPersister = new WillpowerConfigurationPersister();
  private final CharmConfigurationPersister charmPersister = new CharmConfigurationPersister();
  private final SpellConfigurationPersister spellPersister = new SpellConfigurationPersister();
  private final ExperiencePointsPersister experiencePersister = new ExperiencePointsPersister();
  private final RulesPersister rulesPersister = new RulesPersister();
  private final ICharacterGenerics generics;
  private final AdditionalModelPersister additonalModelPersister;

  public CharacterStatisticPersister(INatureProvider natureProvider, ICharacterGenerics generics) {
    IAdditionalPersister virtueFlawPersister = generics.getAdditonalPersisterFactoryRegistry().get(
        IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID).createPersister();
    this.virtuePersister = new VirtueConfigurationPersister(virtueFlawPersister);
    this.generics = generics;
    this.characterConceptPersister = new CharacterConceptPersister(natureProvider);
    this.backgroundPersister = new BackgroundConfigurationPersister(generics.getBackgroundRegistry());
    this.additonalModelPersister = new AdditionalModelPersister(generics.getAdditonalPersisterFactoryRegistry());

  }

  public void save(Element parent, ICharacterStatistics statistics) {
    Ensure.ensureNotNull("Statistics must not be null", statistics); //$NON-NLS-1$
    Element statisticsElement = parent.addElement(TAG_STATISTICS);
    rulesPersister.save(statisticsElement, statistics.getRules());
    statisticsElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(statistics.isExperienced()));
    ICharacterTemplate template = statistics.getCharacterTemplate();
    Element characterTypeElement = statisticsElement.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
    characterConceptPersister.save(statisticsElement, statistics.getCharacterConcept());
    essencePersister.save(statisticsElement, statistics.getTraitConfiguration());
    willpowerPersister.save(statisticsElement, statistics.getTraitConfiguration().getTrait(OtherTraitType.Willpower));
    virtuePersister.save(statisticsElement, statistics.getTraitConfiguration());
    attributePersister.save(statisticsElement, statistics.getTraitConfiguration());
    abilityPersister.save(statisticsElement, statistics.getTraitConfiguration());
    backgroundPersister.save(statisticsElement, statistics.getTraitConfiguration().getBackgrounds());
    charmPersister.save(statisticsElement, statistics);
    spellPersister.save(statisticsElement, statistics.getSpells());
    experiencePersister.save(statisticsElement, statistics.getExperiencePoints());
    additonalModelPersister.save(statisticsElement, statistics.getExtendedConfiguration().getAdditionalModels());
  }

  public void load(Element parent, ICharacter character) throws PersistenceException {
    try {
      Element statisticsElement = parent.element(TAG_STATISTICS);
      if (statisticsElement == null) {
        return;
      }
      ITemplateType templateType = loadTemplateType(statisticsElement);
      boolean experienced = ElementUtilities.getBooleanAttribute(statisticsElement, ATTRIB_EXPERIENCED, false);
      IExaltedRuleSet rules = rulesPersister.load(statisticsElement);
      ICharacterTemplate template = generics.getTemplateRegistry().getTemplate(templateType, rules.getEdition());
      ICharacterStatistics statistics = character.createCharacterStatistics(template, generics, rules);
      ICasteCollection casteCollection = template.getCasteCollection();
      characterConceptPersister.load(statisticsElement, statistics.getCharacterConcept(), casteCollection);
      essencePersister.load(statisticsElement, statistics.getTraitConfiguration());
      IAdditionalModel virtueFlawModel = statistics.getExtendedConfiguration().getAdditionalModel(
          IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID);
      virtuePersister.load(statisticsElement, statistics.getTraitConfiguration(), virtueFlawModel);
      attributePersister.load(statisticsElement, statistics.getTraitConfiguration());
      abilityPersister.load(statisticsElement, statistics.getTraitConfiguration());
      backgroundPersister.load(statisticsElement, statistics.getTraitConfiguration().getBackgrounds());
      charmPersister.load(statisticsElement, statistics);
      spellPersister.load(statisticsElement, statistics.getSpells());
      experiencePersister.load(statisticsElement, statistics.getExperiencePoints());
      statistics.setExperienced(experienced);
      willpowerPersister.load(statisticsElement, statistics.getTraitConfiguration().getTrait(OtherTraitType.Willpower));
      additonalModelPersister.load(statisticsElement, statistics.getExtendedConfiguration().getAdditionalModels());
    }
    catch (CharmException e) {
      throw new PersistenceException(e);
    }
    catch (SpellException e) {
      throw new PersistenceException(e);
    }
  }

  private ITemplateType loadTemplateType(Element parent) throws PersistenceException {
    String typeId = ElementUtilities.getRequiredText(parent, TAG_CHARACTER_TYPE);
    CharacterType characterType;
    String subTypeValue;
    if (typeId.equals(OLD_HEROIC_MORTAL_TYPE_ID.getId())) {
      characterType = CharacterType.MORTAL;
      subTypeValue = OLD_HEROIC_MORTAL_TYPE_ID.getId();
    }
    else {
      characterType = CharacterType.getById(typeId);
      subTypeValue = parent.element(TAG_CHARACTER_TYPE).attributeValue(ATTRIB_SUB_TYPE);
    }
    IIdentificate subtype = subTypeValue == null ? TemplateType.DEFAULT_SUB_TYPE : new Identificate(subTypeValue);
    return new TemplateType(characterType, subtype);
  }
}