package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.traits.lists.AllAttributeTraitTypeList;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.main.xml.abilitygroup.TraitTypeGroupTemplateParser;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.main.xml.creation.CreationPointTemplateParser;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactoryParser;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CharacterTemplateParser extends AbstractXmlTemplateParser<GenericCharacterTemplate> {

  private static final String TAG_ATTRIBUTE_GROUPS = "attributeGroups";
  private static final String TAG_CREATION = "creation";
  private static final String TAG_CREATION_POINTS = "creationPoints";
  private static final String TAG_EXPERIENCE = "experience";
  private static final String TAG_GENERAL = "general";
  private static final String TAG_EXPERIENCE_POINT_COST = "experiencePointCost";
  private static final String TAG_TRAIT_COLLECTION = "traitCollection";
  private static final String TAG_PRESENTATION_TEMPLATE = "presentation";
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
    setGeneralProperties(element, characterTemplate);
    parseModels(element, characterTemplate);
    parseCreation(element, characterTemplate);
    parseExperience(element, characterTemplate);
    return characterTemplate;
  }

  private void updateTemplateType(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    ITemplateType templateType = new TemplateTypeParser(characterTypes).parse(element);
    characterTemplate.setTemplateType(templateType);
  }

  private void setAttributeGroups(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element attributeGroupElement = generalElement.element(TAG_ATTRIBUTE_GROUPS);
    if (attributeGroupElement == null) {
      return;
    }
    IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> registry = registryCollection.getTraitGroupTemplateRegistry();
    TraitTypeGroupTemplateParser parser = new TraitTypeGroupTemplateParser(registry, AllAttributeTraitTypeList.getInstance());
    GenericGroupedTraitTypeProvider attributeGroups = parser.parseTemplate(attributeGroupElement);
    characterTemplate.setAttributeGroups(attributeGroups.getTraitTypeGroups());
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

  private void setGeneralProperties(Element element, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element generalElement = element.element(TAG_GENERAL);
    if (generalElement == null) {
      return;
    }
    setAttributeGroups(generalElement, characterTemplate);
    setTraitCollection(generalElement, characterTemplate);
    setPresentationTemplate(generalElement, characterTemplate);
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

  private void setPresentationTemplate(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element presentationTemplateElement = generalElement.element(TAG_PRESENTATION_TEMPLATE);
    if (presentationTemplateElement == null) {
      return;
    }
    PresentationPropertiesParser parser = new PresentationPropertiesParser(registryCollection.getPresentationTemplateRegistry());
    GenericPresentationTemplate template = parser.parseTemplate(presentationTemplateElement);
    characterTemplate.setPresentationTemplate(template);
  }

  private void setTraitCollection(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element traitCollectionElement = generalElement.element(TAG_TRAIT_COLLECTION);
    if (traitCollectionElement == null) {
      return;
    }
    GenericTraitTemplateFactoryParser parser =
            new GenericTraitTemplateFactoryParser(registryCollection.getTraitFactoryRegistry(), registryCollection.getTraitTemplatePoolRegistry());
    GenericTraitTemplateFactory factory = parser.parseTemplate(traitCollectionElement);
    characterTemplate.setTraitFactory(factory);
  }
}
