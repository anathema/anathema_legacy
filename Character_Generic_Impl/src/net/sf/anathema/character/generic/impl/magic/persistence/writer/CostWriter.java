package net.sf.anathema.character.generic.impl.magic.persistence.writer;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IHealthCost;

import org.dom4j.Element;

public class CostWriter implements ICharmXMLConstants {

  public void write(ICharmData charm, Element charmElement) {
    Element costElement = charmElement.addElement(TAG_COST);
    ICostList temporaryCost = charm.getTemporaryCost();
    if (temporaryCost != null) {
      writeCost(TAG_ESSENCE_COST, temporaryCost.getEssenceCost(), costElement);
      writeCost(TAG_WILLPOWER_COST, temporaryCost.getWillpowerCost(), costElement);
      writeCost(TAG_HEALTH_COST, temporaryCost.getHealthCost(), costElement);
    }
  }

  // ICost xpCost = charm.getPermanentCost().getXPCost();
  // if (!(StringUtilities.isNullOrEmpty(xpCost.getCost()) && StringUtilities.isNullOrEmpty(xpCost.getText()))) {
  // writeCost(TAG_EXPERIENCE_COST, xpCost, costElement.addElement(TAG_PERMANENT));
  // }
  // }
  //
  private void writeCost(String tagName, ICost cost, Element listElement) {
    if (cost == null) {
      return;
    }
    String costString = cost.getCost();
    String textString = cost.getText();
    boolean costEmpty = StringUtilities.isNullOrEmpty(costString);
    boolean textEmpty = StringUtilities.isNullOrEmpty(textString);
    if (costEmpty && textEmpty) {
      return;
    }
    Element costElement = listElement.addElement(tagName);
    if (StringUtilities.isNullOrEmpty(costString)) {
      costString = ""; //$NON-NLS-1$
    }
    costElement.addAttribute(ATTRIB_COST, costString);
    if (!textEmpty) {
      costElement.addAttribute(ATTRIB_TEXT, textString);
    }
    if (cost instanceof IHealthCost) {
      costElement.addAttribute(ATTRIB_TYPE, ((IHealthCost) cost).getType().getId());
    }
  }
}