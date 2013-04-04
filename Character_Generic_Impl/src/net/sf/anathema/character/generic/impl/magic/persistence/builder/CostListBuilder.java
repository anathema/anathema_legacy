package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CostListBuilder implements ICostListBuilder {

  private final CostBuilder costBuilder = new CostBuilder();
  private final HealthCostBuilder healthCostBuilder = new HealthCostBuilder();

  @Override
  public ICostList buildCostList(Element costListElement) throws PersistenceException {
    try {
      ICost essenceCost = costBuilder.buildCost(costListElement.element("essence"));
      ICost willpowerCost = costBuilder.buildCost(costListElement.element("willpower"));
      IHealthCost healthCost = healthCostBuilder.buildCost(costListElement.element("health"));
      ICost xpCost = costBuilder.buildCost(costListElement.element("experience"));
      return new CostList(essenceCost, willpowerCost, healthCost, xpCost);
    }
    catch(Exception e) {
      return new CostList(null, null, null, null);
    }
  }
}