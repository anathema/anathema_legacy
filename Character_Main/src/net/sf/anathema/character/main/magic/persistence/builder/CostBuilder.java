package net.sf.anathema.character.main.magic.persistence.builder;

import net.sf.anathema.character.main.magic.Cost;
import net.sf.anathema.character.main.magic.ICharmXMLConstants;
import net.sf.anathema.character.main.magic.general.ICost;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class CostBuilder {

  public ICost buildCost(Element element) throws PersistenceException {
    if (element == null) {
      return Cost.NULL_COST;
    }
    String costString = ElementUtilities.getRequiredAttrib(element, ICharmXMLConstants.ATTRIB_COST);
    String text = element.attributeValue(ICharmXMLConstants.ATTRIB_TEXT);
    boolean permanent = ElementUtilities.getBooleanAttribute(element, ICharmXMLConstants.ATTRIB_PERMANENT, false);
    return new Cost(costString, text, permanent);
  }
}