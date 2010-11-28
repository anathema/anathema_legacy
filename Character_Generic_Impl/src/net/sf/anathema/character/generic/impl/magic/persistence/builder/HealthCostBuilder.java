package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class HealthCostBuilder {

  public IHealthCost buildCost(Element element) throws PersistenceException {
    if (element == null) {
      return HealthCost.NULL_HEALTH_COST;
    }
    int cost = ElementUtilities.getRequiredIntAttrib(element, ICharmXMLConstants.ATTRIB_COST);
    String text = element.attributeValue(ICharmXMLConstants.ATTRIB_TEXT);
    boolean permanent = ElementUtilities.getBooleanAttribute(element, ICharmXMLConstants.ATTRIB_PERMANENT, false);
    String typeString = element.attributeValue(ICharmXMLConstants.ATTRIB_TYPE);
    HealthType type;
    if (typeString == null) {
      type = HealthType.Lethal;
    }
    else {
      type = HealthType.valueOf(typeString);
    }
    return new HealthCost(cost, text, permanent, type);
  }
}