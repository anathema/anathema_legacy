package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.main.xml.creation.CreationPointTemplateParser;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CharacterTemplateParser extends AbstractXmlTemplateParser<GenericCharacterTemplate> {

  private static final String TAG_CREATION = "creation";
  private static final String TAG_CREATION_POINTS = "creationPoints";
  private static final String TAG_EXPERIENCE = "experience";
  private static final String TAG_EXPERIENCE_POINT_COST = "experiencePointCost";
  public static final String ATTRIB_ID = "id";

  private CharacterTypes characterTypes;
  private final ICharacterTemplateRegistryCollection registryCollection;

  public CharacterTemplateParser(CharacterTypes characterTypes, ICharacterTemplateRegistryCollection registryCollection) {
    super(registryCollection.getCharacterTemplateRegistry());
    this.characterTypes = characterTypes;
    this.registryCollection = registryCollection;
  }

  @Override
  protected GenericCharacterTemplate createNewBasicTemplate() {
    return new GenericCharacterTemplate();
  }

  private void parseCreation(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element creationElement = element.element(TAG_CREATION);
    if (creationElement == null) {
      return;
    }
    setCreationPoints(characterTemplate, creationElement);
    setBonusPoints(characterTemplate, creationElement);
  }

  private void parseExperience(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element creationElement = element.element(TAG_EXPERIENCE);
    if (creationElement == null) {
      return;
    }
    setExperiencePoints(characterTemplate, creationElement);
  }

  @Override
  public GenericCharacterTemplate parseTemplate(Element element) throws PersistenceException {
    GenericCharacterTemplate characterTemplate = new GenericCharacterTemplate();
    updateTemplateType(element, characterTemplate);
    parseModels(element, characterTemplate);
    parseCreation(element, characterTemplate);
    parseExperience(element, characterTemplate);
    return characterTemplate;
  }

  private void updateTemplateType(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    ITemplateType templateType = new TemplateTypeParser(characterTypes).parse(element);
    characterTemplate.setTemplateType(templateType);
  }

  private void setBonusPoints(GenericCharacterTemplate characterTemplate, Element creationElement) throws PersistenceException {
    Element bonusPointsElement = creationElement.element("bonusPointCosts");
    if (bonusPointsElement == null) {
      return;
    }
    BonusPointCostTemplateParser parser = new BonusPointCostTemplateParser(registryCollection.getBonusPointTemplateRegistry());
    GenericBonusPointCosts bonusPoints = parser.parseTemplate(bonusPointsElement);
    characterTemplate.setBonusPointCosts(bonusPoints);
  }

  private void setCreationPoints(GenericCharacterTemplate characterTemplate, Element creationElement) throws PersistenceException {
    Element creationPointsElement = creationElement.element(TAG_CREATION_POINTS);
    if (creationPointsElement == null) {
      return;
    }
    CreationPointTemplateParser parser = createCreationPointTemplateParser();
    GenericCreationPoints creationPoints = parser.parseTemplate(creationPointsElement);
    characterTemplate.setCreationPoints(creationPoints);
  }

  private CreationPointTemplateParser createCreationPointTemplateParser() {
    return new CreationPointTemplateParser(registryCollection.getCreationPointTemplateRegistry());
  }

  private void setExperiencePoints(GenericCharacterTemplate characterTemplate, Element experienceElement) throws PersistenceException {
    Element experiencePointsElement = experienceElement.element(TAG_EXPERIENCE_POINT_COST);
    if (experiencePointsElement == null) {
      return;
    }
    ExperienceTemplateParser parser = new ExperienceTemplateParser(registryCollection.getExperienceTemplateRegistry());
    GenericExperiencePointCosts experienceTemplate = parser.parseTemplate(experiencePointsElement);
    characterTemplate.setExperiencePointCosts(experienceTemplate);
  }

  private void parseModels(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element modelsElement = element.element("models");
    if (modelsElement == null) {
      return;
    }
    for (Object modelElement : modelsElement.elements()) {
      String modelId = ((Element) modelElement).attributeValue("id");
      String modelTemplateId = ((Element) modelElement).attributeValue("template");
      characterTemplate.addModel(modelId, modelTemplateId);
    }
  }
}