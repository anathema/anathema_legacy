package net.sf.anathema.character.main.xml.creation;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.util.CostParser;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class BonusPointCostTemplateParser extends AbstractXmlTemplateParser<GenericBonusPointCosts> {

  private static final String ATTRIB_DOTS = "dots";
  private static final String ATTRIB_RANK = "rank";
  private static final String ATTRIB_KEYWORD = "keyword";
  private static final String ATTRIB_GENERAL_COST = "generalCost";
  private static final String ATTRIB_FAVORED_COST = "favoredCost";

  private static final String TAG_ATTRIBUTES = "attributes";
  private static final String TAG_GENERAL_ATTRIBUTE = "generalAttribute";
  private static final String TAG_FAVORED_ATTRIBUTE = "favoredAttribute";
  private static final String TAG_ABILITIES = "abilities";
  private static final String TAG_GENERAL_ABILITY = "generalAbility";
  private static final String TAG_FAVORED_ABILITY = "favoredAbility";
  private static final String TAG_SPECIALTIES = "specialties";
  private static final String TAG_GENERAL_DOTS_PER_POINT = "generalDotsPerPoint";
  private static final String TAG_FAVORED_DOTS_PER_POINT = "favoredDotsPerPoint";
  private static final String TAG_ADVANTAGES = "advantages";
  private static final String TAG_BACKGROUNDS = "backgrounds";
  private static final String TAG_VIRTUES = "virtues";
  private static final String TAG_WILLPOWER = "willpower";
  private static final String TAG_ESSENCE = "essence";
  private static final String TAG_CHARMS = "charms";
  private static final String TAG_GENERAL_CHARMS = "generalCharms";
  private static final String TAG_FAVORED_CHARMS = "favoredCharms";
  private static final String TAG_KEYWORD_CHARMS = "keywordCharms";
  private static final String TAG_MAXIMUM_FREE_ABILITY_RANK = "maximumFreeAbilityRank";
  private static final String TAG_MAXIMUM_FREE_VIRTUE_RANK = "maximumFreeVirtueRank";
  private static final String TAG_GENERAL_MARTIAL_ARTS_CHARMS = "generalHighLevelMartialArtsCharms";
  private static final String TAG_FAVORED_MARTIAL_ARTS_CHARMS = "favoredHighLevelMartialArtsCharms";

  private final CostParser costParser = new CostParser();
  private final MartialArtsLevel standardMartialArtsLevel;

  public BonusPointCostTemplateParser(IXmlTemplateRegistry<GenericBonusPointCosts> registry, MartialArtsLevel martialArtsLevel) {
    super(registry);
    this.standardMartialArtsLevel = martialArtsLevel;
  }

  @Override
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
    int generalHighLevelMartialArtsCharmCost =
            costParser.getFixedCostFromOptionalElement(charmElement, TAG_GENERAL_MARTIAL_ARTS_CHARMS, generalCharmCost);
    int favoredHighLevelMartialArtsCharmCost =
            costParser.getFixedCostFromOptionalElement(charmElement, TAG_FAVORED_MARTIAL_ARTS_CHARMS, favoredCharmCost);
    Map<String, Integer> keywordGeneralCost = new HashMap<>();
    Map<String, Integer> keywordFavoredCost = new HashMap<>();
    for (Object keywordNode : charmElement.elements(TAG_KEYWORD_CHARMS)) {
      Element keywordClass = (Element) keywordNode;
      String keyword = ElementUtilities.getRequiredAttrib(keywordClass, ATTRIB_KEYWORD);
      int generalCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_GENERAL_COST);
      int favoredCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_FAVORED_COST);
      keywordGeneralCost.put(keyword, generalCost);
      keywordFavoredCost.put(keyword, favoredCost);
    }

    costs.setCharmCosts(generalCharmCost, favoredCharmCost, generalHighLevelMartialArtsCharmCost, favoredHighLevelMartialArtsCharmCost,
            keywordGeneralCost, keywordFavoredCost);
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
    CurrentRatingCosts essenceCost = costParser.getCosts(essenceElement);
    costs.setEssenceCosts(essenceCost);
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
    CurrentRatingCosts virtueCost = costParser.getFixedCost(virtueElement);
    costs.setVirtueCosts(virtueCost);
    Element maximumFreeRank = virtueElement.element(TAG_MAXIMUM_FREE_VIRTUE_RANK);
    if (maximumFreeRank != null) {
      costs.setMaximumFreeVirtueRank(ElementUtilities.getRequiredIntAttrib(maximumFreeRank, ATTRIB_RANK));
    }
  }

  private void setBackgroundCosts(Element element, GenericBonusPointCosts costs) throws PersistenceException {
    Element backgroundElement = element.element(TAG_BACKGROUNDS);
    if (backgroundElement == null) {
      return;
    }
    CurrentRatingCosts cost = costParser.getThresholdRatingCosts(backgroundElement);
    costs.setBackgroundCosts(cost);
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