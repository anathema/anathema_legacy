package net.sf.anathema.character.generic.framework.xml.util;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CostParser {

  private static final String TAG_FIXED_COST = "fixedCost"; //$NON-NLS-1$
  private static final String ATTRIB_COST = "cost"; //$NON-NLS-1$

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
}