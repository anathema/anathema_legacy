package net.sf.anathema.character.main.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
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

  private final CharacterConceptPersister characterConceptPersister = new CharacterConceptPersister();
  private final ExperiencePointsPersister experiencePersister = new ExperiencePointsPersister();
  private final RulesPersister rulesPersister = new RulesPersister();
  private final HeroEnvironment generics;

  public CharacterStatisticPersister(HeroEnvironment generics) {
    this.generics = generics;
  }

  public void save(Element parent, Hero hero) {
    Preconditions.checkNotNull(hero);
    Element statisticsElement = parent.addElement(TAG_STATISTICS);
    rulesPersister.save(statisticsElement);
    statisticsElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(ExperienceModelFetcher.fetch(hero).isExperienced()));
    HeroTemplate template = hero.getTemplate();
    Element characterTypeElement = statisticsElement.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
    characterConceptPersister.save(statisticsElement, HeroConceptFetcher.fetch(hero));
    experiencePersister.save(statisticsElement, ExperienceModelFetcher.fetch(hero).getExperiencePoints());
  }

  public ExaltedCharacter loadTemplate(Element parent) {
    Element statisticsElement = parent.element(TAG_STATISTICS);
    ITemplateType templateType = loadTemplateType(statisticsElement);
    HeroTemplate template = generics.getTemplateRegistry().getTemplate(templateType);
    return new ExaltedCharacter(template, generics);
  }

  public ExaltedCharacter loadData(ExaltedCharacter character, Element parent) throws PersistenceException {
    Element statisticsElement = parent.element(TAG_STATISTICS);
    boolean experienced = ElementUtilities.getBooleanAttribute(statisticsElement, ATTRIB_EXPERIENCED, false);
    characterConceptPersister.load(statisticsElement, HeroConceptFetcher.fetch(character));
    ExperienceModelFetcher.fetch(character).setExperienced(experienced);
    experiencePersister.load(statisticsElement, ExperienceModelFetcher.fetch(character).getExperiencePoints());
    return character;
  }

  private ITemplateType loadTemplateType(Element parent) throws PersistenceException {
    String typeId = ElementUtilities.getRequiredText(parent, TAG_CHARACTER_TYPE);
    ICharacterType characterType = generics.getCharacterTypes().findById(typeId);
    String subTypeValue = parent.element(TAG_CHARACTER_TYPE).attributeValue(ATTRIB_SUB_TYPE);
    Identifier subtype = subTypeValue == null ? TemplateType.DEFAULT_SUB_TYPE : new SimpleIdentifier(subTypeValue);
    return new TemplateType(characterType, subtype);
  }
}