package net.sf.anathema.character.generic.framework.xml.creation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.util.CostParser;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class BonusPointCostTemplateParser extends AbstractXmlTemplateParser<GenericBonusPointCosts> {

  private static final String ATTRIB_DOTS = "dots"; //$NON-NLS-1$
  private static final String ATTRIB_RANK = "rank"; //$NON-NLS-1$
  private static final String ATTRIB_KEYWORD = "keyword";
  private static final String ATTRIB_GENERAL_COST = "generalCost";
  private static final String ATTRIB_FAVORED_COST = "favoredCost";

  private static final String TAG_ATTRIBUTES = "attributes"; //$NON-NLS-1$
  private static final String TAG_GENERAL_ATTRIBUTE = "generalAttribute"; //$NON-NLS-1$
  private static final String TAG_FAVORED_ATTRIBUTE = "favoredAttribute"; //$NON-NLS-1$
  private static final String TAG_ABILITIES = "abilities"; //$NON-NLS-1$
  private static final String TAG_GENERAL_ABILITY = "generalAbility"; //$NON-NLS-1$
  private static final String TAG_FAVORED_ABILITY = "favoredAbility"; //$NON-NLS-1$
  private static final String TAG_SPECIALTIES = "specialties"; //$NON-NLS-1$
  private static final String TAG_GENERAL_DOTS_PER_POINT = "generalDotsPerPoint"; //$NON-NLS-1$
  private static final String TAG_FAVORED_DOTS_PER_POINT = "favoredDotsPerPoint"; //$NON-NLS-1$
  private static final String TAG_ADVANTAGES = "advantages"; //$NON-NLS-1$
  private static final String TAG_BACKGROUNDS = "backgrounds"; //$NON-NLS-1$
  private static final String TAG_LOW_RATINGS = "lowRatings"; //$NON-NLS-1$
  private static final String TAG_HIGH_RATINGS = "highRatings"; //$NON-NLS-1$
  private static final String TAG_VIRTUES = "virtues"; //$NON-NLS-1$
  private static final String TAG_WILLPOWER = "willpower"; //$NON-NLS-1$
  private static final String TAG_ESSENCE = "essence"; //$NON-NLS-1$
  private static final String TAG_CHARMS = "charms"; //$NON-NLS-1$
  private static final String TAG_GENERAL_CHARMS = "generalCharms"; //$NON-NLS-1$
  private static final String TAG_FAVORED_CHARMS = "favoredCharms"; //$NON-NLS-1$
  private static final String TAG_KEYWORD_CHARMS = "keywordCharms";
  private static final String TAG_MAXIMUM_FREE_ABILITY_RANK = "maximumFreeAbilityRank"; //$NON-NLS-1$
  private static final String TAG_MAXIMUM_FREE_VIRTUE_RANK = "maximumFreeVirtueRank"; //$NON-NLS-1$
  private static final String TAG_GENERAL_MARTIAL_ARTS_CHARMS = "generalHighLevelMartialArtsCharms"; //$NON-NLS-1$
  private static final String TAG_FAVORED_MARTIAL_ARTS_CHARMS = "favoredHighLevelMartialArtsCharms"; //$NON-NLS-1$

  private final CostParser costParser = new CostParser();
  private final MartialArtsLevel standardMartialArtsLevel;

  public BonusPointCostTemplateParser(
      IXmlTemplateRegistry<GenericBonusPointCosts> registry,
      MartialArtsLevel martialArtsLevel) {
    super(registry);
    this.standardMartialArtsLevel = martialArtsLevel;
  }

  public GenericBonusPointCosts parseTemplate(Element element) throws PersistenceException {
    GenericBonusPointCosts costs = getBasicTemplate(element);
    setAttributeCost(element, costs);
    setAbilityCosts(element, costs);
    setAdvantageCosts(element, costs);
    setCharmCosts(element, costs);
    return costs;

  }

  private void setCharmCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element charmElement = element.element(TAG_CHARMS);
    if (charmElement == null) {
      return;
    }
    int generalCharmCost = costParser.getFixedCostFromRequiredElement(charmElement, TAG_GENERAL_CHARMS);
    int favoredCharmCost = costParser.getFixedCostFromRequiredElement(charmElement, TAG_FAVORED_CHARMS);
    int generalHighLevelMartialArtsCharmCost = costParser.getFixedCostFromOptionalElement(
        charmElement,
        TAG_GENERAL_MARTIAL_ARTS_CHARMS,
        generalCharmCost);
    int favoredHighLevelMartialArtsCharmCost = costParser.getFixedCostFromOptionalElement(
        charmElement,
        TAG_FAVORED_MARTIAL_ARTS_CHARMS,
        favoredCharmCost);
    Map<String, Integer> keywordGeneralCost = new HashMap<String, Integer>();
    Map<String, Integer> keywordFavoredCost = new HashMap<String, Integer>();
    for (Object keywordNode : charmElement.elements(TAG_KEYWORD_CHARMS))
    {
    	Element keywordClass = (Element) keywordNode;
    	String keyword = ElementUtilities.getRequiredAttrib(keywordClass, ATTRIB_KEYWORD);
    	int generalCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_GENERAL_COST);
    	int favoredCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_FAVORED_COST);
    	keywordGeneralCost.put(keyword, generalCost);
    	keywordFavoredCost.put(keyword, favoredCost);
    }
    
    costs.setCharmCosts(
        generalCharmCost,
        favoredCharmCost,
        generalHighLevelMartialArtsCharmCost,
        favoredHighLevelMartialArtsCharmCost,
        keywordGeneralCost,
        keywordFavoredCost);
    costs.setStandardMartialArtsLevel(standardMartialArtsLevel);
  }

  private void setAdvantageCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element advantageElement = element.element(TAG_ADVANTAGES);
    if (advantageElement == null) {
      return;
    }
    setBackgroundCosts(advantageElement, costs);
    setVirtueCosts(advantageElement, costs);
    setWillpowerCosts(advantageElement, costs);
    setEssenceCosts(advantageElement, costs);
  }

  private void setEssenceCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element essenceElement = element.element(TAG_ESSENCE);
    if (essenceElement == null) {
      return;
    }
    int fixedCost = costParser.getFixedCostFromRequiredElement(element, TAG_ESSENCE);
    costs.setEssenceCosts(fixedCost);
  }

  private void setWillpowerCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element willpowerElement = element.element(TAG_WILLPOWER);
    if (willpowerElement == null) {
      return;
    }
    int fixedCost = costParser.getFixedCostFromRequiredElement(element, TAG_WILLPOWER);
    costs.setWillpowerCosts(fixedCost);
  }

  private void setVirtueCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element virtueElement = element.element(TAG_VIRTUES);
    if (virtueElement == null) {
      return;
    }
    int fixedCost = costParser.getFixedCostFromRequiredElement(element, TAG_VIRTUES);
    Element maximumFreeRank = virtueElement.element(TAG_MAXIMUM_FREE_VIRTUE_RANK);
    if (maximumFreeRank != null) {
      costs.setMaximumFreeVirtueRank(ElementUtilities.getRequiredIntAttrib(maximumFreeRank, ATTRIB_RANK));
    }
    costs.setVirtueCosts(fixedCost);
  }

  private void setBackgroundCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element backgroundElement = element.element(TAG_BACKGROUNDS);
    if (backgroundElement == null) {
      return;
    }
    int lowCost = costParser.getFixedCostFromRequiredElement(backgroundElement, TAG_LOW_RATINGS);
    int highCost = costParser.getFixedCostFromRequiredElement(backgroundElement, TAG_HIGH_RATINGS);
    costs.setBackgroundCosts(lowCost, highCost);
  }

  private void setSpecialtyDots(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element specialtyElement = element.element(TAG_SPECIALTIES);
    if (specialtyElement == null) {
      return;
    }
    Element generalSpecialtyDotsElement = specialtyElement.element(TAG_GENERAL_DOTS_PER_POINT);
    if (generalSpecialtyDotsElement != null) {
      costs.setGeneralSpecialtyDots(ElementUtilities.getRequiredIntAttrib(generalSpecialtyDotsElement, ATTRIB_DOTS));
    }
    Element favoredSpecialtyDotsElement = specialtyElement.element(TAG_FAVORED_DOTS_PER_POINT);
    if (favoredSpecialtyDotsElement != null) {
      costs.setFavoredSpecialtyDots(ElementUtilities.getRequiredIntAttrib(favoredSpecialtyDotsElement, ATTRIB_DOTS));
    }
  }

  private void setAbilityCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element abilityElement = element.element(TAG_ABILITIES);
    if (abilityElement == null) {
      return;
    }
    int generalCost = costParser.getFixedCostFromRequiredElement(abilityElement, TAG_GENERAL_ABILITY);
    int favoredCost = costParser.getFixedCostFromRequiredElement(abilityElement, TAG_FAVORED_ABILITY);
    
    Element maximumFreeRank = abilityElement.element(TAG_MAXIMUM_FREE_ABILITY_RANK);
    if (maximumFreeRank != null) {
      costs.setMaximumFreeAbilityRank(ElementUtilities.getRequiredIntAttrib(maximumFreeRank, ATTRIB_RANK));
    }
    costs.setAbilityCosts(generalCost, favoredCost);
    setSpecialtyDots(abilityElement, costs);
  }

  private void setAttributeCost(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element attributeElement = element.element(TAG_ATTRIBUTES);
    if (attributeElement == null) {
      return;
    }
    int generalCost = costParser.getFixedCostFromRequiredElement(attributeElement, TAG_GENERAL_ATTRIBUTE);
    int favoredCost = costParser.getFixedCostFromOptionalElement(attributeElement, TAG_FAVORED_ATTRIBUTE, generalCost);
    costs.setAttributeCost(generalCost, favoredCost);
  }

  @Override
  protected GenericBonusPointCosts createNewBasicTemplate() {
    return new GenericBonusPointCosts();
  }
}