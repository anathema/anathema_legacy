package net.sf.anathema.character.main.xml.experience;

import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.util.CostParser;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

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
  private final CostParser costParser = new CostParser();

  public ExperienceTemplateParser(IXmlTemplateRegistry<GenericExperiencePointCosts> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericExperiencePointCosts createNewBasicTemplate() {
    return new GenericExperiencePointCosts();
  }

  @Override
  public GenericExperiencePointCosts parseTemplate(Element element) throws PersistenceException {
    GenericExperiencePointCosts costs = getBasicTemplate(element);
    setAttributeCosts(element, costs);
    setAbilityCosts(element, costs);
    setAdvantageCosts(element, costs);
    return costs;
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