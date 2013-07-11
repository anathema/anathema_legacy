package net.sf.anathema.character.main.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.character.main.magic.model.spells.SpellException;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModelFetcher;
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

  private final CharacterConceptPersister characterConceptPersister = new CharacterConceptPersister();
  private final EssenceConfigurationPersister essencePersister = new EssenceConfigurationPersister();
  private final VirtueConfigurationPersister virtuePersister = new VirtueConfigurationPersister();
  private final WillpowerConfigurationPersister willpowerPersister = new WillpowerConfigurationPersister();
  private final ExperiencePointsPersister experiencePersister = new ExperiencePointsPersister();
  private final RulesPersister rulesPersister = new RulesPersister();
  private final HeroEnvironment generics;
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();

  public CharacterStatisticPersister(HeroEnvironment generics) {
    this.generics = generics;
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
    experiencePersister.save(statisticsElement, ExperienceModelFetcher.fetch(hero).getExperiencePoints());
  }

  public ExaltedCharacter loadTemplate(Element parent) {
    Element statisticsElement = parent.element(TAG_STATISTICS);
    ITemplateType templateType = loadTemplateType(statisticsElement);
    HeroTemplate template = generics.getTemplateRegistry().getTemplate(templateType);
    return new ExaltedCharacter(template, generics);
  }

  public ExaltedCharacter loadData(ExaltedCharacter character, Element parent) throws PersistenceException {
    try {
      Element statisticsElement = parent.element(TAG_STATISTICS);
      boolean experienced = ElementUtilities.getBooleanAttribute(statisticsElement, ATTRIB_EXPERIENCED, false);
      HeroDescription characterDescription = HeroDescriptionFetcher.fetch(character);
      descriptionPersister.load(parent, characterDescription);
      HeroConcept concept = HeroConceptFetcher.fetch(character);
      characterConceptPersister.load(statisticsElement, concept, characterDescription);
      ExperienceModelFetcher.fetch(character).setExperienced(experienced);
      essencePersister.load(statisticsElement, TraitModelFetcher.fetch(character));
      virtuePersister.load(statisticsElement, TraitModelFetcher.fetch(character));
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