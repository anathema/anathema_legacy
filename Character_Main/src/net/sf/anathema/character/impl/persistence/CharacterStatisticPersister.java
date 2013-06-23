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
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.attributes.AttributesModelFetcher;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
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
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();

  public CharacterStatisticPersister(ICharacterGenerics generics, IMessaging messaging) {
    this.generics = generics;
    this.charmPersister = new CharmConfigurationPersister(messaging);
  }

  public void save(Element parent, ICharacter character) {
    Preconditions.checkNotNull(character);
    descriptionPersister.save(parent, HeroDescriptionFetcher.fetch(character));
    Element statisticsElement = parent.addElement(TAG_STATISTICS);
    rulesPersister.save(statisticsElement);
    statisticsElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(ExperienceModelFetcher.fetch(character).isExperienced()));
    HeroTemplate template = character.getTemplate();
    Element characterTypeElement = statisticsElement.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
    characterConceptPersister.save(statisticsElement, HeroConceptFetcher.fetch(character));
    essencePersister.save(statisticsElement, TraitModelFetcher.fetch(character));
    willpowerPersister.save(statisticsElement, TraitModelFetcher.fetch(character).getTrait(OtherTraitType.Willpower));
    virtuePersister.save(statisticsElement, TraitModelFetcher.fetch(character));
    attributePersister.save(statisticsElement, AttributesModelFetcher.fetch(character));
    abilityPersister.save(statisticsElement, AbilityModelFetcher.fetch(character), SpecialtiesModelFetcher.fetch(character));
    charmPersister.save(statisticsElement, character);
    spellPersister.save(statisticsElement, SpellsModelFetcher.fetch(character));
    experiencePersister.save(statisticsElement, ExperienceModelFetcher.fetch(character).getExperiencePoints());
  }

  public ExaltedCharacter load(Element parent) throws PersistenceException {
    try {
      Element statisticsElement = parent.element(TAG_STATISTICS);
      ITemplateType templateType = loadTemplateType(statisticsElement);
      boolean experienced = ElementUtilities.getBooleanAttribute(statisticsElement, ATTRIB_EXPERIENCED, false);
      HeroTemplate template = generics.getTemplateRegistry().getTemplate(templateType);
      ExaltedCharacter character = new ExaltedCharacter(template, generics);
      HeroDescription characterDescription = HeroDescriptionFetcher.fetch(character);
      descriptionPersister.load(parent, characterDescription);
      ICasteCollection casteCollection = template.getCasteCollection();
      characterConceptPersister.load(statisticsElement, HeroConceptFetcher.fetch(character), characterDescription, casteCollection);
      ExperienceModelFetcher.fetch(character).setExperienced(experienced);
      essencePersister.load(statisticsElement, TraitModelFetcher.fetch(character));
      virtuePersister.load(statisticsElement, TraitModelFetcher.fetch(character));
      attributePersister.load(statisticsElement, AttributesModelFetcher.fetch(character));
      abilityPersister.load(statisticsElement, AbilityModelFetcher.fetch(character), SpecialtiesModelFetcher.fetch(character));
      charmPersister.load(statisticsElement, character);
      spellPersister.load(statisticsElement, SpellsModelFetcher.fetch(character));
      experiencePersister.load(statisticsElement, ExperienceModelFetcher.fetch(character).getExperiencePoints());
      willpowerPersister.load(statisticsElement, TraitModelFetcher.fetch(character).getTrait(OtherTraitType.Willpower));
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