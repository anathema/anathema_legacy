package net.sf.anathema.character.impl.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.persistence.charm.CharmConfigurationPersister;
import net.sf.anathema.character.main.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.concept.model.CharacterConceptFetcher;
import net.sf.anathema.character.main.description.model.CharacterDescription;
import net.sf.anathema.character.main.description.model.CharacterDescriptionFetcher;
import net.sf.anathema.character.main.experience.model.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_SUB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_STATISTICS;

public class CharacterStatisticPersister {

  private final AttributeConfigurationPersister attributePersister = new AttributeConfigurationPersister();
  private final AbilityConfigurationPersister abilityPersister = new AbilityConfigurationPersister();
  private final CharacterConceptPersister characterConceptPersister = new CharacterConceptPersister();
  private final EssenceConfigurationPersister essencePersister = new EssenceConfigurationPersister();
  private final VirtueConfigurationPersister virtuePersister = new VirtueConfigurationPersister();
  private final WillpowerConfigurationPersister willpowerPersister = new WillpowerConfigurationPersister();
  private final CharmConfigurationPersister charmPersister;
  private final SpellConfigurationPersister spellPersister = new SpellConfigurationPersister();
  private final ExperiencePointsPersister experiencePersister = new ExperiencePointsPersister();
  private final RulesPersister rulesPersister = new RulesPersister();
  private final ICharacterGenerics generics;
  private final AdditionalModelPersister additonalModelPersister;
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();

  public CharacterStatisticPersister(ICharacterGenerics generics, IMessaging messaging) {
    this.generics = generics;
    this.charmPersister = new CharmConfigurationPersister(messaging);
    this.additonalModelPersister = new AdditionalModelPersister(generics.getAdditonalPersisterFactoryRegistry(), messaging);
  }

  public void save(Element parent, ICharacter character) {
    Preconditions.checkNotNull(character);
    descriptionPersister.save(parent, CharacterDescriptionFetcher.fetch(character));
    Element statisticsElement = parent.addElement(TAG_STATISTICS);
    rulesPersister.save(statisticsElement);
    statisticsElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(ExperienceModelFetcher.fetch(character).isExperienced()));
    HeroTemplate template = character.getHeroTemplate();
    Element characterTypeElement = statisticsElement.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
    characterConceptPersister.save(statisticsElement, CharacterConceptFetcher.fetch(character));
    essencePersister.save(statisticsElement, character.getTraitModel());
    willpowerPersister.save(statisticsElement, character.getTraitModel().getTrait(OtherTraitType.Willpower));
    virtuePersister.save(statisticsElement, character.getTraitModel());
    attributePersister.save(statisticsElement, character.getAttributes());
    abilityPersister.save(statisticsElement, AbilityModelFetcher.fetch(character));
    charmPersister.save(statisticsElement, character);
    spellPersister.save(statisticsElement, character.getSpells());
    experiencePersister.save(statisticsElement, ExperienceModelFetcher.fetch(character).getExperiencePoints());
    additonalModelPersister.save(statisticsElement, character.getExtendedConfiguration().getAdditionalModels());
  }

  public ExaltedCharacter load(Element parent) throws PersistenceException {
    try {
      Element statisticsElement = parent.element(TAG_STATISTICS);
      ITemplateType templateType = loadTemplateType(statisticsElement);
      boolean experienced = ElementUtilities.getBooleanAttribute(statisticsElement, ATTRIB_EXPERIENCED, false);
      HeroTemplate template = generics.getTemplateRegistry().getTemplate(templateType);
      ExaltedCharacter character = new ExaltedCharacter(template, generics);
      CharacterDescription characterDescription = CharacterDescriptionFetcher.fetch(character);
      descriptionPersister.load(parent, characterDescription);
      ICasteCollection casteCollection = template.getCasteCollection();
      characterConceptPersister.load(statisticsElement, CharacterConceptFetcher.fetch(character), characterDescription, casteCollection);
      ExperienceModelFetcher.fetch(character).setExperienced(experienced);
      essencePersister.load(statisticsElement, character.getTraitModel());
      virtuePersister.load(statisticsElement, character.getTraitModel());
      attributePersister.load(statisticsElement, character.getAttributes());
      abilityPersister.load(statisticsElement, AbilityModelFetcher.fetch(character));
      charmPersister.load(statisticsElement, character);
      spellPersister.load(statisticsElement, character.getSpells());
      experiencePersister.load(statisticsElement, ExperienceModelFetcher.fetch(character).getExperiencePoints());
      willpowerPersister.load(statisticsElement, character.getTraitModel().getTrait(OtherTraitType.Willpower));
      additonalModelPersister.load(statisticsElement, character.getExtendedConfiguration().getAdditionalModels());
      return character;
    } catch (CharmException | SpellException e) {
      throw new PersistenceException(e);
    }
  }

  private ITemplateType loadTemplateType(Element parent) throws PersistenceException {
    String typeId = ElementUtilities.getRequiredText(parent, TAG_CHARACTER_TYPE);
    ICharacterType characterType = generics.getCharacterTypes().findById(typeId);
    String subTypeValue = parent.element(TAG_CHARACTER_TYPE).attributeValue(ATTRIB_SUB_TYPE);
    Identifier subtype = subTypeValue == null ? TemplateType.DEFAULT_SUB_TYPE : new SimpleIdentifier(subTypeValue);
    return new TemplateType(characterType, subtype);
  }
}