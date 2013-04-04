package net.sf.anathema.character.generic.framework.xml.magic;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.magic.UniqueCharmType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.impl.template.magic.NullCharmSet;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmSet;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GenericMagicTemplateParser extends AbstractXmlTemplateParser<GenericMagicTemplate> {

  private static final String TAG_UNIQUE_CHARM_TYPE = "hasUniqueCharmType";
  private static final String TAG_FREE_PICKS_PREDICATE = "freePicksPredicate";
  private static final String ATTRIB_TYPE = "type";
  private static final String ATTRIB_LABEL = "label";
  private static final String ATTRIB_KEYWORD = "keyword";
  private static final String TAG_CHARM_TEMPLATE = "charmTemplate";
  private static final String ATTRIB_MARTIAL_ARTS_LEVEL = "level";
  private static final String ATTRIB_HIGH_LEVEL_MARTIAL_ARTS = "highLevel";
  private static final String ATTRIB_CHARM_TYPE = "charmType";
  private static final Object VALUE_NONE = "None";
  private static final String TAG_SPELL_TEMPLATE = "spellTemplate";
  private static final String ATTRIB_MAXIMUM_SORCERY_CIRCLE = "maximumSorceryCircle";
  private static final String ATTRIB_MAXIMUM_NECROMANCY_CIRCLE = "maximumNecromancyCircle";
  private static final String TAG_CASTE = "caste";
  private static final String TAG_ALIEN_CHARMS = "alienCharms";
  private static final String TAG_MARTIAL_ARTS = "martialArts";
  private static final String ATTRIB_RULES_CLASS = "rulesClass";
  private static final String ATTRIB_DEFAULT_RESPONSE = "defaultResponse";
  private static final String TAG_ID_EXCEPTION = "idException";
  private static final String ATTRIB_ID = "id";
  private static final String TAG_GROUP_EXCEPTION = "groupException";
  private static final String ATTRIB_SUB_TEMPLATE = "subTemplate";
  private final ICharacterTemplate hostTemplate;
  private final ICharmCache cache;
  private final CharacterTypes characterTypes;

  public GenericMagicTemplateParser(IXmlTemplateRegistry<GenericMagicTemplate> templateRegistry,
                                    ICharacterTemplate template,
                                    ICharmCache cache, CharacterTypes characterTypes) {
    super(templateRegistry);
    this.hostTemplate = template;
    this.cache = cache;
    this.characterTypes = characterTypes;
  }

  @Override
  protected GenericMagicTemplate createNewBasicTemplate() {
    return new GenericMagicTemplate();
  }

  @Override
  public GenericMagicTemplate parseTemplate(Element element) throws PersistenceException {
    GenericMagicTemplate basicTemplate = getBasicTemplate(element);
    setFreePicksPredicate(basicTemplate, element);
    setCharmTemplate(basicTemplate, element);
    setSpellTemplate(basicTemplate, element);
    return basicTemplate;
  }

  @SuppressWarnings("unchecked")
  private void setSpellTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element spellTemplateElement = element.element(TAG_SPELL_TEMPLATE);
    if (spellTemplateElement == null) {
      return;
    }
    String maximumSorceryCircleId = ElementUtilities.getRequiredAttrib(spellTemplateElement,
            ATTRIB_MAXIMUM_SORCERY_CIRCLE);
    CircleType maximumSorceryCircle = null;
    if (!maximumSorceryCircleId.equals(VALUE_NONE)) {
      maximumSorceryCircle = CircleType.valueOf(maximumSorceryCircleId);
    }
    String maximumNecromancyCircleId = ElementUtilities.getRequiredAttrib(spellTemplateElement,
            ATTRIB_MAXIMUM_NECROMANCY_CIRCLE);
    CircleType maximumNecromancyCircle = null;
    if (!maximumNecromancyCircleId.equals(VALUE_NONE)) {
      maximumNecromancyCircle = CircleType.valueOf(maximumNecromancyCircleId);
    }
    Class<SpellMagicTemplate> magicTemplateClass = SpellMagicTemplate.class;
    ISpellMagicTemplate template = null;
    String spellMagicSubTemplate = spellTemplateElement.attributeValue(ATTRIB_SUB_TEMPLATE);
    if (spellMagicSubTemplate != null) {
      try {
        magicTemplateClass = (Class<SpellMagicTemplate>) Class.forName(spellMagicSubTemplate);
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }
    }

    try {
      Constructor<SpellMagicTemplate>[] helper = (Constructor<SpellMagicTemplate>[]) magicTemplateClass.getConstructors();
      template = helper[0].newInstance(CircleType.getSorceryCirclesUpTo(maximumSorceryCircle),
              CircleType.getNecromancyCirclesUpTo(maximumNecromancyCircle), hostTemplate);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    basicTemplate.setSpellTemplate(template);
  }

  private void setCharmTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element charmTemplateElement = element.element(TAG_CHARM_TEMPLATE);
    if (charmTemplateElement == null) {
      return;
    }
    String charmType = ElementUtilities.getRequiredAttrib(charmTemplateElement, ATTRIB_CHARM_TYPE);
    ICharmSet charmSet;
    IUniqueCharmType uniqueCharms = null;
    for (Object node : charmTemplateElement.elements(TAG_UNIQUE_CHARM_TYPE)) {
      Element specialNode = (Element) node;
      String specialType = ElementUtilities.getRequiredAttrib(specialNode, ATTRIB_TYPE);
      String specialLabel = specialNode.attributeValue(ATTRIB_LABEL);
      String specialKeyword = specialNode.attributeValue(ATTRIB_KEYWORD);
      uniqueCharms = new UniqueCharmType(specialType, specialLabel, specialKeyword);
    }
    if (charmType.equals(VALUE_NONE)) {
      charmSet = new NullCharmSet();
    } else {
      charmSet = CharmSet.createRegularCharmSet(cache, characterTypes.findById(charmType), uniqueCharms);
    }
    CharmTemplate charmTemplate = new CharmTemplate(createMartialArtsRules(charmTemplateElement), charmSet,
            uniqueCharms);
    setAlienAllowedCastes(charmTemplate, charmTemplateElement);
    basicTemplate.setCharmTemplate(charmTemplate);
  }

  private MartialArtsRules createMartialArtsRules(Element parent) throws PersistenceException {
    Element martialArtsElement = ElementUtilities.getRequiredElement(parent, TAG_MARTIAL_ARTS);
    MartialArtsLevel level = getMartialArtsLevel(martialArtsElement);
    String rulesClassName = martialArtsElement.attributeValue(ATTRIB_RULES_CLASS);
    MartialArtsRules rules;
    if (rulesClassName == null) {
      rules = new DefaultMartialArtsRules(level);
    } else {
      try {
        rules = (MartialArtsRules) Class.forName(rulesClassName).newInstance();
      } catch (Exception e) {
        throw new PersistenceException(e);
      }
    }
    boolean highLevelAtCreation = ElementUtilities.getBooleanAttribute(martialArtsElement,
            ATTRIB_HIGH_LEVEL_MARTIAL_ARTS, false);
    rules.setHighLevelAtCreation(highLevelAtCreation);
    return rules;
  }

  private MartialArtsLevel getMartialArtsLevel(Element martialArtsElement) throws PersistenceException {
    String martialArtsLevelId = ElementUtilities.getRequiredAttrib(martialArtsElement, ATTRIB_MARTIAL_ARTS_LEVEL);
    return MartialArtsLevel.valueOf(martialArtsLevelId);
  }

  private void setAlienAllowedCastes(CharmTemplate charmTemplate,
                                     Element charmTemplateElement) throws PersistenceException {
    Element alienCharmsElement = charmTemplateElement.element(TAG_ALIEN_CHARMS);
    if (alienCharmsElement == null) {
      return;
    }
    List<Element> casteElements = ElementUtilities.elements(alienCharmsElement, TAG_CASTE);
    for (Element casteElement : casteElements) {
      charmTemplate.setCasteAlienAllowed(ElementUtilities.getRequiredAttrib(casteElement, ATTRIB_TYPE));
    }
  }

  private void setFreePicksPredicate(GenericMagicTemplate basicTemplate, Element element) {
    Element freePicksElement = element.element(TAG_FREE_PICKS_PREDICATE);
    if (freePicksElement == null) {
      return;
    }
    boolean response = ElementUtilities.getBooleanAttribute(freePicksElement, ATTRIB_DEFAULT_RESPONSE, true);
    CustomizableFreePicksPredicate predicate = new CustomizableFreePicksPredicate(response);
    for (Element idException : ElementUtilities.elements(freePicksElement, TAG_ID_EXCEPTION)) {
      predicate.addIdException(idException.attributeValue(ATTRIB_ID));
    }
    for (Element idException : ElementUtilities.elements(freePicksElement, TAG_GROUP_EXCEPTION)) {
      predicate.addCharmGroupException(idException.attributeValue(ATTRIB_ID));
    }
    basicTemplate.setFreePicksPredicate(predicate);
  }
}