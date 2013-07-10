package net.sf.anathema.character.main.xml.experience;

import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.util.CostParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class ExperienceTemplateParser extends AbstractXmlTemplateParser<GenericExperiencePointCosts> {

  private static final String ATTRIB_KEYWORD = "keyword";
  private static final String ATTRIB_FAVORED = "favored";
  private static final String ATTRIB_GENERAL = "general";
  private static final String ATTRIB_COST = "cost";

  private static final String TAG_GENERAL_COSTS = "generalCosts";
  private static final String TAG_FAVORED_COSTS = "favoredCosts";
  private static final String TAG_ATTRIBUTES = "attributes";
  private static final String TAG_ABILITIES = "abilities";
  private static final String TAG_SPECIALTIES = "specialties";
  private static final String TAG_ADVANTAGES = "advantages";
  private static final String TAG_WILLPOWER = "willpower";
  private static final String TAG_VIRTUE = "virtues";
  private static final String TAG_ESSENCE = "essence";
  private static final String TAG_MAGIC = "magic";
  private static final String TAG_CHARMS = "charms";
  private static final String TAG_SPELLS = "spells";
  private static final String TAG_KEYWORD_CHARMS = "keywordCharms";
  private static final String TAG_MARTIAL_ARTS = "highLevelMartialArts";
  private final CostParser costParser = new CostParser();
  private final MartialArtsLevel standardLevel;

  public ExperienceTemplateParser(IXmlTemplateRegistry<GenericExperiencePointCosts> templateRegistry, MartialArtsLevel standardLevel) {
    super(templateRegistry);
    this.standardLevel = standardLevel;
  }

  @Override
  protected GenericExperiencePointCosts createNewBasicTemplate() {
    return new GenericExperiencePointCosts();
  }

  @Override
  public GenericExperiencePointCosts parseTemplate(Element element) throws PersistenceException {
    GenericExperiencePointCosts costs = getBasicTemplate(element);
    costs.setStandardMartialArtsLevel(standardLevel);
    setAttributeCosts(element, costs);
    setAbilityCosts(element, costs);
    setAdvantageCosts(element, costs);
    setMagicCosts(element, costs);
    return costs;
  }

  private void setMagicCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element magic = element.element(TAG_MAGIC);
    if (magic == null) {
      return;
    }
    Element charms = magic.element(TAG_CHARMS);
    int favoredCost = ElementUtilities.getRequiredIntAttrib(charms, ATTRIB_FAVORED);
    int generalCost = ElementUtilities.getRequiredIntAttrib(charms, ATTRIB_GENERAL);
    Map<String, Integer> keywordGeneralCost = new HashMap<>();
    Map<String, Integer> keywordFavoredCost = new HashMap<>();

    for (Object node : charms.elements(TAG_KEYWORD_CHARMS)) {
      Element keywordClass = (Element) node;
      String keyword = ElementUtilities.getRequiredAttrib(keywordClass, ATTRIB_KEYWORD);
      int gCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_GENERAL);
      int fCost = ElementUtilities.getRequiredIntAttrib(keywordClass, ATTRIB_FAVORED);
      keywordGeneralCost.put(keyword, gCost);
      keywordFavoredCost.put(keyword, fCost);
    }

    costs.setCharmCosts(favoredCost, generalCost, keywordGeneralCost, keywordFavoredCost);
    Element martialArts = charms.element(TAG_MARTIAL_ARTS);
    int favoredMartialArtsCost = ElementUtilities.getRequiredIntAttrib(martialArts, ATTRIB_FAVORED);
    int generalMartialArtsCost = ElementUtilities.getRequiredIntAttrib(martialArts, ATTRIB_GENERAL);
    costs.setMartialArtsCosts(favoredMartialArtsCost, generalMartialArtsCost);

    Element spells = magic.element(TAG_SPELLS);
    if (spells != null) {
      int cost = ElementUtilities.getIntAttrib(spells, ATTRIB_COST, 0);
      costs.setSpellCost(cost);
    }

  }

  private void setAdvantageCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element advantages = element.element(TAG_ADVANTAGES);
    if (advantages == null) {
      return;
    }
    setWillpowerCosts(costs, advantages);
    setVirtueCosts(costs, advantages);
    setEssenceCosts(costs, advantages);
  }

  private void setEssenceCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_ESSENCE);
    if (element == null) {
      return;
    }
    CurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setEssenceCosts(ratingCosts);
  }

  private void setVirtueCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_VIRTUE);
    if (element == null) {
      return;
    }
    CurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setVirtueCosts(ratingCosts);
  }

  private void setWillpowerCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_WILLPOWER);
    if (element == null) {
      return;
    }
    CurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setWillpowerCosts(ratingCosts);
  }

  private void setAbilityCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element abilities = element.element(TAG_ABILITIES);
    if (abilities == null) {
      return;
    }
    costs.setGeneralAbilityCosts(getGeneralCost(abilities));
    costs.setFavoredAbilityCosts(getFavoredCost(abilities));
    setSpecialtyCosts(abilities, costs);
  }

  private void setSpecialtyCosts(Element abilities, GenericExperiencePointCosts costs) throws PersistenceException {
    int specialtyCost = costParser.getFixedCostFromRequiredElement(abilities, TAG_SPECIALTIES);
    costs.setSpecialtyCosts(specialtyCost);
  }

  private void setAttributeCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element attributes = element.element(TAG_ATTRIBUTES);
    if (attributes == null) {
      return;
    }
    costs.setGeneralAttributeCosts(getGeneralCost(attributes));
    costs.setFavoredAttributeCosts(getFavoredCost(attributes));
  }

  protected final CurrentRatingCosts getFavoredCost(Element attributes) throws PersistenceException {
    return new CostParser().getMultiplyRatingCostsFromRequiredElement(attributes, TAG_FAVORED_COSTS);
  }

  protected final CurrentRatingCosts getGeneralCost(Element attributes) throws PersistenceException {
    return new CostParser().getMultiplyRatingCostsFromRequiredElement(attributes, TAG_GENERAL_COSTS);
  }

  protected final CurrentRatingCosts getCurrentRatingCosts(Element element) throws PersistenceException {
    return new CostParser().getMultiplyRatingCosts(element);
  }
}