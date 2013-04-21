package net.sf.anathema.character.generic.framework.xml.util;

import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class CostParser {

  private static final String TAG_FIXED_COST = "fixedCost";
  private static final String ATTRIB_COST = "cost";

  private static final String ATTRIB_INITIALCOST = "initialCosts";
  private static final String ATTRIB_MULTIPLIER = "multiplier";
  private static final String ATTRIB_SUMMAND = "summand";
  private static final String TAG_CURRENT_RATING_COSTS = "currentRating";

  public int getFixedCostFromRequiredElement(Element element, String elementName) throws PersistenceException {
    Element parentElement = ElementUtilities.getRequiredElement(element, elementName);
    Element fixedCostElement = ElementUtilities.getRequiredElement(parentElement, TAG_FIXED_COST);
    return ElementUtilities.getRequiredIntAttrib(fixedCostElement, ATTRIB_COST);
  }

  public int getFixedCostFromOptionalElement(Element element, String elementName, int defaultValue)
      throws PersistenceException {
    Element parentElement = element.element(elementName);
    if (parentElement == null) {
      return defaultValue;
    }
    Element fixedCostElement = ElementUtilities.getRequiredElement(parentElement, TAG_FIXED_COST);
    return ElementUtilities.getRequiredIntAttrib(fixedCostElement, ATTRIB_COST);
  }

  public CurrentRatingCosts getMultiplyRatingCosts(Element parentElement) throws PersistenceException {
    Element element = ElementUtilities.getRequiredElement(parentElement, TAG_CURRENT_RATING_COSTS);
    int multiplier = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_MULTIPLIER);
    int summand = ElementUtilities.getIntAttrib(element, ATTRIB_SUMMAND, 0);
    int initialCost = ElementUtilities.getIntAttrib(element, ATTRIB_INITIALCOST, Integer.MIN_VALUE);
    return new MultiplyRatingCosts(multiplier, initialCost, summand);
  }
}