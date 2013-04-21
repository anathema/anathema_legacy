package net.sf.anathema.character.generic.framework.xml.util;

import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.impl.template.points.ThresholdRatingCosts;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import static net.sf.anathema.lib.xml.ElementUtilities.getIntAttrib;
import static net.sf.anathema.lib.xml.ElementUtilities.getRequiredElement;
import static net.sf.anathema.lib.xml.ElementUtilities.getRequiredIntAttrib;

public class CostParser {

  private static final String TAG_FIXED_COST = "fixedCost";
  private static final String ATTRIB_COST = "cost";

  private static final String ATTRIB_INITIALCOST = "initialCosts";
  private static final String ATTRIB_MULTIPLIER = "multiplier";
  private static final String ATTRIB_SUMMAND = "summand";
  private static final String TAG_CURRENT_RATING_COSTS = "currentRating";

  private static final String TAG_THRESHOLD_COST = "thresholdCost";
  private static final String ATTRIB_LOW_COST= "lowCost";
  private static final String ATTRIB_HIGH_COST= "highCost";
  private static final String ATTRIB_THRESHOLD= "threshold";

  public int getFixedCostFromRequiredElement(Element element, String elementName) throws PersistenceException {
    Element parentElement = getRequiredElement(element, elementName);
    Element fixedCostElement = getRequiredElement(parentElement, TAG_FIXED_COST);
    return getRequiredIntAttrib(fixedCostElement, ATTRIB_COST);
  }

  public int getFixedCostFromOptionalElement(Element element, String elementName, int defaultValue)
          throws PersistenceException {
    Element parentElement = element.element(elementName);
    if (parentElement == null) {
      return defaultValue;
    }
    Element fixedCostElement = getRequiredElement(parentElement, TAG_FIXED_COST);
    return getRequiredIntAttrib(fixedCostElement, ATTRIB_COST);
  }

  public CurrentRatingCosts getMultiplyRatingCostsFromRequiredElement(Element parentElement, String tagName)
          throws PersistenceException {
    Element element = getRequiredElement(parentElement, tagName);
    return getMultiplyRatingCosts(element);
  }

  public CurrentRatingCosts getMultiplyRatingCosts(Element parentElement) throws PersistenceException {
    Element element = getRequiredElement(parentElement, TAG_CURRENT_RATING_COSTS);
    int multiplier = getRequiredIntAttrib(element, ATTRIB_MULTIPLIER);
    int summand = getIntAttrib(element, ATTRIB_SUMMAND, 0);
    int initialCost = getIntAttrib(element, ATTRIB_INITIALCOST, Integer.MIN_VALUE);
    return new MultiplyRatingCosts(multiplier, initialCost, summand);
  }

  public CurrentRatingCosts getThresholdRatingCosts(Element parentElement) {
    Element costElement = getRequiredElement(parentElement, TAG_THRESHOLD_COST);
    int lowCost = getRequiredIntAttrib(costElement, ATTRIB_LOW_COST);
    int highCost = getRequiredIntAttrib(costElement, ATTRIB_HIGH_COST);
    int threshold = getRequiredIntAttrib(costElement, ATTRIB_THRESHOLD);
    return new ThresholdRatingCosts(lowCost, highCost, threshold);
  }
}