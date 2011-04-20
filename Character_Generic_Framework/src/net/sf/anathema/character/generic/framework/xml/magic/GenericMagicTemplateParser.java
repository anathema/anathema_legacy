package net.sf.anathema.character.generic.framework.xml.magic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.magic.UniqueRequiredCharmType;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.impl.template.magic.ICharmSet;
import net.sf.anathema.character.generic.impl.template.magic.NullCharmSet;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.magic.IUniqueRequiredCharmType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericMagicTemplateParser extends AbstractXmlTemplateParser<GenericMagicTemplate> {

  private static final String TAG_UNIQUE_CHARM_TYPE = "hasUniqueCharmType"; //$NON-NLS-1$
  private static final String TAG_FREE_PICKS_PREDICATE = "freePicksPredicate"; //$NON-NLS-1$
  private static final String ATTRIB_CAN_FAVOR = "canCountAsFavored"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String TAG_CHARM_TEMPLATE = "charmTemplate"; //$NON-NLS-1$
  private static final String ATTRIB_MARTIAL_ARTS_LEVEL = "level"; //$NON-NLS-1$
  private static final String ATTRIB_HIGH_LEVEL_MARTIAL_ARTS = "highLevel"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_TYPE = "charmType"; //$NON-NLS-1$
  private static final Object VALUE_NONE = "None"; //$NON-NLS-1$
  private static final String TAG_SPELL_TEMPLATE = "spellTemplate"; //$NON-NLS-1$
  private static final String ATTRIB_MAXIMUM_SORCERY_CIRCLE = "maximumSorceryCircle"; //$NON-NLS-1$
  private static final String TAG_FAVORING_TRAIT_TYPE = "favoringTraitType"; //$NON-NLS-1$
  private static final String ATTRIB_MAXIMUM_NECROMANCY_CIRCLE = "maximumNecromancyCircle"; //$NON-NLS-1$
  private static final String TAG_CASTE = "caste"; //$NON-NLS-1$
  private static final String TAG_ALIEN_CHARMS = "alienCharms"; //$NON-NLS-1$
  private static final String TAG_MARTIAL_ARTS = "martialArts"; //$NON-NLS-1$
  private static final String ATTRIB_RULES_CLASS = "rulesClass"; //$NON-NLS-1$
  private static final String ATTRIB_DEFAULT_RESPONSE = "defaultResponse"; //$NON-NLS-1$
  private static final String TAG_ID_EXCEPTION = "idException"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_GROUP_EXCEPTION = "groupException"; //$NON-NLS-1$
  private static final String ATTRIB_SUB_TEMPLATE = "subTemplate";
  private final ICharacterTemplate hostTemplate;

  public GenericMagicTemplateParser(IXmlTemplateRegistry<GenericMagicTemplate> templateRegistry, ICharacterTemplate template) {
    super(templateRegistry);
    this.hostTemplate = template;
  }

  @Override
  protected GenericMagicTemplate createNewBasicTemplate() {
    return new GenericMagicTemplate();
  }

  public GenericMagicTemplate parseTemplate(Element element) throws PersistenceException {
    GenericMagicTemplate basicTemplate = getBasicTemplate(element);
    setFreePicksPredicate(basicTemplate, element);
    setFavoringTraitType(basicTemplate, element);
    setCharmTemplate(basicTemplate, element);
    setSpellTemplate(basicTemplate, element);
    return basicTemplate;
  }

  private void setFavoringTraitType(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element traitTypeElement = element.element(TAG_FAVORING_TRAIT_TYPE);
    if (traitTypeElement == null) {
      return;
    }
    String type = ElementUtilities.getRequiredAttrib(traitTypeElement, ATTRIB_TYPE);
    FavoringTraitType traitTypeType = FavoringTraitType.valueOf(type);
    basicTemplate.setFavoringTraitType(traitTypeType);
  }

  @SuppressWarnings("unchecked")
private void setSpellTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element spellTemplateElement = element.element(TAG_SPELL_TEMPLATE);
    if (spellTemplateElement == null) {
      return;
    }
    String maximumSorceryCircleId = ElementUtilities.getRequiredAttrib(
        spellTemplateElement,
        ATTRIB_MAXIMUM_SORCERY_CIRCLE);
    CircleType maximumSorceryCircle = null;
    if (!maximumSorceryCircleId.equals(VALUE_NONE)) {
      maximumSorceryCircle = CircleType.valueOf(maximumSorceryCircleId);
    }
    String maximumNecromancyCircleId = ElementUtilities.getRequiredAttrib(
        spellTemplateElement,
        ATTRIB_MAXIMUM_NECROMANCY_CIRCLE);
    CircleType maximumNecromancyCircle = null;
    if (!maximumNecromancyCircleId.equals(VALUE_NONE)) {
      maximumNecromancyCircle = CircleType.valueOf(maximumNecromancyCircleId);
    }
    Class<SpellMagicTemplate> magicTemplateClass = SpellMagicTemplate.class;
    ISpellMagicTemplate template = null;
    String spellMagicSubTemplate = spellTemplateElement.attributeValue(ATTRIB_SUB_TEMPLATE);
    if (spellMagicSubTemplate != null)
    {
    	try {
			magicTemplateClass = (Class<SpellMagicTemplate>) Class.forName(spellMagicSubTemplate);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
    }
    
    try {
		Constructor[] helper = magicTemplateClass.getConstructors();
		template = (ISpellMagicTemplate) helper[0].newInstance(CircleType.getSorceryCirclesUpTo(maximumSorceryCircle),
	                CircleType.getNecromancyCirclesUpTo(maximumNecromancyCircle),
	                hostTemplate);
			
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
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
    if (charmType.equals(VALUE_NONE)) {
      charmSet = new NullCharmSet();
    }
    else {
      charmSet = CharmSet.createRegularCharmSet(CharmCache.getInstance(), CharacterType.getById(charmType), hostTemplate.getEdition());
    }
    IUniqueRequiredCharmType specialCharms = null;
    for (Object node : charmTemplateElement.elements(TAG_UNIQUE_CHARM_TYPE))
    {
    	Element specialNode = (Element) node;
    	String specialName = ElementUtilities.getRequiredAttrib(specialNode,
    			ATTRIB_NAME);
    	String specialType = ElementUtilities.getRequiredAttrib(specialNode,
    			ATTRIB_TYPE);
    	boolean canFavor = ElementUtilities.getBooleanAttribute(specialNode,
    			ATTRIB_CAN_FAVOR, true);
    	specialCharms = new UniqueRequiredCharmType(specialName, specialType, canFavor);
    }
    CharmTemplate charmTemplate = new CharmTemplate(createMartialArtsRules(charmTemplateElement),
    		charmSet, specialCharms);
    setAlienAllowedCastes(charmTemplate, charmTemplateElement);
    basicTemplate.setCharmTemplate(charmTemplate);
  }

  private IMartialArtsRules createMartialArtsRules(Element parent) throws PersistenceException {
    Element martialArtsElement = ElementUtilities.getRequiredElement(parent, TAG_MARTIAL_ARTS);
    MartialArtsLevel level = getMartialArtsLevel(martialArtsElement);
    String rulesClassName = martialArtsElement.attributeValue(ATTRIB_RULES_CLASS);
    IMartialArtsRules rules;
    if (rulesClassName == null) {
      rules = new DefaultMartialArtsRules(level);
    }
    else {
      try {
        rules = (IMartialArtsRules) Class.forName(rulesClassName).newInstance();
      }
      catch (Exception e) {
        throw new PersistenceException(e);
      }
    }
    boolean highLevelAtCreation = ElementUtilities.getBooleanAttribute(
        martialArtsElement,
        ATTRIB_HIGH_LEVEL_MARTIAL_ARTS,
        false);
    rules.setHighLevelAtCreation(highLevelAtCreation);
    return rules;
  }

  private MartialArtsLevel getMartialArtsLevel(Element martialArtsElement) throws PersistenceException {
    String martialArtsLevelId = ElementUtilities.getRequiredAttrib(martialArtsElement, ATTRIB_MARTIAL_ARTS_LEVEL);
    return MartialArtsLevel.valueOf(martialArtsLevelId);
  }

  private void setAlienAllowedCastes(CharmTemplate charmTemplate, Element charmTemplateElement)
      throws PersistenceException {
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