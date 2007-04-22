package net.sf.anathema.character.generic.framework.xml;

import java.util.List;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.framework.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.generic.framework.xml.abilitygroup.TraitTypeGroupTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.CreationPointTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.framework.xml.essence.EssenceTemplateParser;
import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.framework.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.generic.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.health.HealthTemplateParser;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.rules.AdditionalRulesTemplateParser;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactoryParser;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.AllAttributeTraitTypeGroup;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharacterTemplateParser extends AbstractXmlTemplateParser<GenericCharacterTemplate> {

  private static final String TAG_ABILITY_GROUPS = "abilityGroups"; //$NON-NLS-1$
  private static final String TAG_ATTRIBUTE_GROUPS = "attributeGroups"; //$NON-NLS-1$
  private static final String TAG_CREATION = "creation"; //$NON-NLS-1$
  private static final String TAG_CREATION_POINTS = "creationPoints"; //$NON-NLS-1$
  private static final String TAG_ESSENCE = "essence"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE = "experience"; //$NON-NLS-1$
  private static final String TAG_GENERAL = "general"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE_POINT_COST = "experiencePointCost"; //$NON-NLS-1$
  private static final String TAG_TRAIT_COLLECTION = "traitCollection"; //$NON-NLS-1$
  private static final String TAG_MAGIC_TEMPLATE = "magicTemplate"; //$NON-NLS-1$
  private static final String TAG_PRESENTATION_TEMPLATE = "presentation"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_TEMPLATES = "additionalTemplates"; //$NON-NLS-1$
  private static final String TAG_TEMPLATE = "template"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_HEALTH_TEMPLATE = "healthTemplate"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_RULES = "additionalRules"; //$NON-NLS-1$
  private static final String TAG_EDITION = "edition"; //$NON-NLS-1$
  private static final String ATTRIB_EDITION = "edition"; //$NON-NLS-1$

  private final ICharacterTemplateRegistryCollection registryCollection;
  private final IRegistry<ICharacterType, ICasteCollection> casteCollectionRegistry;
  private final IRegistry<String, IAdditionalTemplateParser> additionModelTemplateParserRegistry;
  private final ICharmProvider provider;
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;

  public CharacterTemplateParser(
      ICharacterTemplateRegistryCollection registryCollection,
      IRegistry<ICharacterType, ICasteCollection> casteCollectionRegistry,
      ICharmProvider provider,
      IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry,
      IRegistry<String, IAdditionalTemplateParser> additionModelTemplateParser) {
    super(registryCollection.getCharacterTemplateRegistry());
    this.registryCollection = registryCollection;
    this.casteCollectionRegistry = casteCollectionRegistry;
    this.provider = provider;
    this.backgroundRegistry = backgroundRegistry;
    this.additionModelTemplateParserRegistry = additionModelTemplateParser;
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

  public GenericCharacterTemplate parseTemplate(Element element) throws PersistenceException {
    GenericCharacterTemplate characterTemplate = new GenericCharacterTemplate();
    updateTemplateType(element, characterTemplate);
    setGeneralProperties(element, characterTemplate);
    parseCreation(element, characterTemplate);
    parseExperience(element, characterTemplate);
    return characterTemplate;
  }

  private void updateTemplateType(Element element, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    ITemplateType templateType = new TemplateTypeParser().parse(element);
    characterTemplate.setTemplateType(templateType);
    ICasteCollection casteCollection = casteCollectionRegistry.get(templateType.getCharacterType());
    if (casteCollection != null) {
      characterTemplate.setCasteCollection(casteCollection);
    }
  }

  private void setAbilityGroups(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element abilityGroupElement = generalElement.element(TAG_ABILITY_GROUPS);
    if (abilityGroupElement == null) {
      return;
    }
    IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> registry = registryCollection.getTraitGroupTemplateRegistry();
    TraitTypeGroupTemplateParser parser = new TraitTypeGroupTemplateParser(
        registry,
        AllAbilityTraitTypeGroup.getInstance());
    GenericGroupedTraitTypeProvider abilityGroups = parser.parseTemplate(abilityGroupElement);
    characterTemplate.setAbilityGroups(abilityGroups.getTraitTypeGroups());
  }

  private void setAttributeGroups(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element attributeGroupElement = generalElement.element(TAG_ATTRIBUTE_GROUPS);
    if (attributeGroupElement == null) {
      return;
    }
    IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> registry = registryCollection.getTraitGroupTemplateRegistry();
    TraitTypeGroupTemplateParser parser = new TraitTypeGroupTemplateParser(
        registry,
        AllAttributeTraitTypeGroup.getInstance());
    GenericGroupedTraitTypeProvider attributeGroups = parser.parseTemplate(attributeGroupElement);
    characterTemplate.setAttributeGroups(attributeGroups.getTraitTypeGroups());
  }

  private void setBonusPoints(GenericCharacterTemplate characterTemplate, Element creationElement)
      throws PersistenceException {
    Element bonusPointsElement = creationElement.element("bonusPointCosts"); //$NON-NLS-1$
    if (bonusPointsElement == null) {
      return;
    }
    BonusPointCostTemplateParser parser = new BonusPointCostTemplateParser(
        registryCollection.getBonusPointTemplateRegistry(),
        characterTemplate.getMagicTemplate().getCharmTemplate().getMartialArtsRules().getStandardLevel());
    GenericBonusPointCosts bonusPoints = parser.parseTemplate(bonusPointsElement);
    characterTemplate.setBonusPointCosts(bonusPoints);
  }

  private void setCreationPoints(GenericCharacterTemplate characterTemplate, Element creationElement)
      throws PersistenceException {
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

  private void setEssenceTemplate(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element essenceElement = generalElement.element(TAG_ESSENCE);
    if (essenceElement == null) {
      return;
    }
    EssenceTemplateParser parser = new EssenceTemplateParser(registryCollection.getEssenceTemplateRegistry());
    GenericEssenceTemplate essenceTemplate = parser.parseTemplate(essenceElement);
    characterTemplate.setEssenceTemplate(essenceTemplate);
  }

  private void setExperiencePoints(GenericCharacterTemplate characterTemplate, Element experienceElement)
      throws PersistenceException {
    Element experiencePointsElement = experienceElement.element(TAG_EXPERIENCE_POINT_COST);
    if (experiencePointsElement == null) {
      return;
    }
    ExperienceTemplateParser parser = new ExperienceTemplateParser(
        registryCollection.getExperienceTemplateRegistry(),
        characterTemplate.getMagicTemplate().getCharmTemplate().getMartialArtsRules().getStandardLevel());
    GenericExperiencePointCosts experienceTemplate = parser.parseTemplate(experiencePointsElement);
    characterTemplate.setExperiencePointCosts(experienceTemplate);
  }

  private void setGeneralProperties(Element element, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element generalElement = element.element(TAG_GENERAL);
    if (generalElement == null) {
      return;
    }
    setEdition(generalElement, characterTemplate);
    setAbilityGroups(generalElement, characterTemplate);
    setAttributeGroups(generalElement, characterTemplate);
    setEssenceTemplate(generalElement, characterTemplate);
    setTraitCollection(generalElement, characterTemplate);
    setMagicTemplate(generalElement, characterTemplate);
    setPresentationTemplate(generalElement, characterTemplate);
    setAdditionalModelTemplates(generalElement, characterTemplate);
    setAdditionalRules(generalElement, characterTemplate);
    setToughnessControllingTrait(generalElement, characterTemplate);
  }

  private void setEdition(Element generalElement, GenericCharacterTemplate characterTemplate) {
    Element element = generalElement.element(TAG_EDITION);
    if (element == null) {
      return;
    }
    String edition = element.attributeValue(ATTRIB_EDITION);
    characterTemplate.setEdition(ExaltedEdition.valueOf(edition));
  }

  private void setAdditionalRules(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element element = generalElement.element(TAG_ADDITIONAL_RULES);
    if (element == null) {
      return;
    }
    AdditionalRulesTemplateParser parser = new AdditionalRulesTemplateParser(
        registryCollection.getAdditionalRulesRegistry(),
        provider.getSpecialCharms(
            characterTemplate.getTemplateType().getCharacterType(),
            characterTemplate.getEdition()),
        backgroundRegistry);
    GenericAdditionalRules rules = parser.parseTemplate(element);
    characterTemplate.setAdditionalRules(rules);
  }

  private void setToughnessControllingTrait(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element healthElement = generalElement.element(TAG_HEALTH_TEMPLATE);
    if (healthElement == null) {
      return;
    }
    HealthTemplateParser parser = new HealthTemplateParser(registryCollection.getHealthTemplateRegistry());
    GenericHealthTemplate template = parser.parseTemplate(healthElement);
    characterTemplate.setHealthTemplate(template);
  }

  private void setAdditionalModelTemplates(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element additionalElement = generalElement.element(TAG_ADDITIONAL_TEMPLATES);
    if (additionalElement == null) {
      return;
    }
    List<Element> templateElements = ElementUtilities.elements(additionalElement, TAG_TEMPLATE);
    for (Element templateElement : templateElements) {
      String id = ElementUtilities.getRequiredAttrib(templateElement, ATTRIB_ID);
      IAdditionalTemplate template = additionModelTemplateParserRegistry.get(id).parse(templateElement);
      characterTemplate.addAdditionalTemplate(template);
    }
  }

  private void setPresentationTemplate(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element presentationTemplateElement = generalElement.element(TAG_PRESENTATION_TEMPLATE);
    if (presentationTemplateElement == null) {
      return;
    }
    PresentationPropertiesParser parser = new PresentationPropertiesParser(
        registryCollection.getPresentationTemplateRegistry(),
        registryCollection.getCharmPresentationTemplateRegistry());
    GenericPresentationTemplate template = parser.parseTemplate(presentationTemplateElement);
    characterTemplate.setPresentationTemplate(template);
  }

  private void setMagicTemplate(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element magicTemplateElement = generalElement.element(TAG_MAGIC_TEMPLATE);
    if (magicTemplateElement == null) {
      return;
    }
    GenericMagicTemplateParser parser = new GenericMagicTemplateParser(
        registryCollection.getMagicTemplateRegistry(),
        characterTemplate.getEdition());
    GenericMagicTemplate template = parser.parseTemplate(magicTemplateElement);
    characterTemplate.setMagicTemplate(template);
  }

  private void setTraitCollection(Element generalElement, GenericCharacterTemplate characterTemplate)
      throws PersistenceException {
    Element traitCollectionElement = generalElement.element(TAG_TRAIT_COLLECTION);
    if (traitCollectionElement == null) {
      return;
    }
    GenericTraitTemplateFactoryParser parser = new GenericTraitTemplateFactoryParser(
        registryCollection.getTraitFactoryRegistry(),
        registryCollection.getTraitTemplatePoolRegistry(),
        backgroundRegistry);
    GenericTraitTemplateFactory factory = parser.parseTemplate(traitCollectionElement);
    characterTemplate.setTraitFactory(factory);
  }
}