package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.traits.lists.AllAbilityTraitTypeList;
import net.sf.anathema.character.main.traits.lists.AllAttributeTraitTypeList;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.main.xml.abilitygroup.TraitTypeGroupTemplateParser;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.main.xml.creation.CreationPointTemplateParser;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.essence.EssenceTemplateParser;
import net.sf.anathema.character.main.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.main.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.main.xml.health.HealthTemplateParser;
import net.sf.anathema.character.main.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.main.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactoryParser;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CharacterTemplateParser extends AbstractXmlTemplateParser<GenericCharacterTemplate> {

  private static final String TAG_ABILITY_GROUPS = "abilityGroups";
  private static final String TAG_ATTRIBUTE_GROUPS = "attributeGroups";
  private static final String TAG_CREATION = "creation";
  private static final String TAG_CREATION_POINTS = "creationPoints";
  private static final String TAG_ESSENCE = "essence";
  private static final String TAG_EXPERIENCE = "experience";
  private static final String TAG_GENERAL = "general";
  private static final String TAG_EXPERIENCE_POINT_COST = "experiencePointCost";
  private static final String TAG_TRAIT_COLLECTION = "traitCollection";
  private static final String TAG_MAGIC_TEMPLATE = "magicTemplate";
  private static final String TAG_PRESENTATION_TEMPLATE = "presentation";
  public static final String ATTRIB_ID = "id";
  private static final String TAG_HEALTH_TEMPLATE = "healthTemplate";

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

  private void setAbilityGroups(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element abilityGroupElement = generalElement.element(TAG_ABILITY_GROUPS);
    if (abilityGroupElement == null) {
      return;
    }
    IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> registry = registryCollection.getTraitGroupTemplateRegistry();
    TraitTypeGroupTemplateParser parser = new TraitTypeGroupTemplateParser(registry, AllAbilityTraitTypeList.getInstance());
    GenericGroupedTraitTypeProvider abilityGroups = parser.parseTemplate(abilityGroupElement);
    characterTemplate.setAbilityGroups(abilityGroups.getTraitTypeGroups());
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

  private void setEssenceTemplate(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element essenceElement = generalElement.element(TAG_ESSENCE);
    if (essenceElement == null) {
      return;
    }
    EssenceTemplateParser parser = new EssenceTemplateParser(registryCollection.getEssenceTemplateRegistry());
    GenericEssenceTemplate essenceTemplate = parser.parseTemplate(essenceElement);
    characterTemplate.setEssenceTemplate(essenceTemplate);
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
    setAbilityGroups(generalElement, characterTemplate);
    setAttributeGroups(generalElement, characterTemplate);
    setEssenceTemplate(generalElement, characterTemplate);
    setTraitCollection(generalElement, characterTemplate);
    setMagicTemplate(generalElement, characterTemplate);
    setPresentationTemplate(generalElement, characterTemplate);
    setToughnessControllingTrait(generalElement, characterTemplate);
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

  private void setToughnessControllingTrait(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element healthElement = generalElement.element(TAG_HEALTH_TEMPLATE);
    if (healthElement == null) {
      return;
    }
    HealthTemplateParser parser = new HealthTemplateParser(registryCollection.getHealthTemplateRegistry());
    GenericHealthTemplate template = parser.parseTemplate(healthElement);
    characterTemplate.setHealthTemplate(template);
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

  private void setMagicTemplate(Element generalElement, GenericCharacterTemplate characterTemplate) throws PersistenceException {
    Element magicTemplateElement = generalElement.element(TAG_MAGIC_TEMPLATE);
    if (magicTemplateElement == null) {
      return;
    }
    GenericMagicTemplateParser parser = new GenericMagicTemplateParser(registryCollection.getMagicTemplateRegistry(), characterTemplate);
    GenericMagicTemplate template = parser.parseTemplate(magicTemplateElement);
    characterTemplate.setMagicTemplate(template);
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
