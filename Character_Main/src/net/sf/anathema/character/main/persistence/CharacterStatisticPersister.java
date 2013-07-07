package net.sf.anathema.character.main.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.caste.ICasteCollection;
import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.character.main.magic.SpellException;
import net.sf.anathema.character.main.magic.charms.CharmException;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.hero.abilities.AbilityModelFetcher;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.persistence.charm.CharmConfigurationPersister;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCED;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_SUB_TYPE;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARACTER_TYPE;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_STATISTICS;

public class CharacterStatisticPersister {

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

  public void save(Element parent, Hero hero) {
    Preconditions.checkNotNull(hero);
    descriptionPersister.save(parent, HeroDescriptionFetcher.fetch(hero));
    Element statisticsElement = parent.addElement(TAG_STATISTICS);
    rulesPersister.save(statisticsElement);
    statisticsElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(ExperienceModelFetcher.fetch(hero).isExperienced()));
    HeroTemplate template = hero.getTemplate();
    Element characterTypeElement = statisticsElement.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
    characterConceptPersister.save(statisticsElement, HeroConceptFetcher.fetch(hero));
    essencePersister.save(statisticsElement, TraitModelFetcher.fetch(hero));
    willpowerPersister.save(statisticsElement, TraitModelFetcher.fetch(hero).getTrait(OtherTraitType.Willpower));
    virtuePersister.save(statisticsElement, TraitModelFetcher.fetch(hero));
    abilityPersister.save(statisticsElement, AbilityModelFetcher.fetch(hero), SpecialtiesModelFetcher.fetch(hero));
    charmPersister.save(statisticsElement, hero);
    spellPersister.save(statisticsElement, SpellsModelFetcher.fetch(hero));
    experiencePersister.save(statisticsElement, ExperienceModelFetcher.fetch(hero).getExperiencePoints());
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