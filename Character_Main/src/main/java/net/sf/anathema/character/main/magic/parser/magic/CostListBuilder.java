package net.sf.anathema.character.main.magic.parser.magic;

import net.sf.anathema.character.main.magic.basic.cost.Cost;
import net.sf.anathema.character.main.magic.basic.cost.CostList;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.basic.cost.IHealthCost;
import net.sf.anathema.character.main.magic.parser.charms.HealthCostBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class CostListBuilder implements ICostListBuilder {

  private final CostBuilder costBuilder = new CostBuilder();
  private final HealthCostBuilder healthCostBuilder = new HealthCostBuilder();

  @Override
  public ICostList buildCostList(Element costListElement) throws PersistenceException {
    try {
      Cost essenceCost = costBuilder.buildCost(costListElement.element("essence"));
      Cost willpowerCost = costBuilder.buildCost(costListElement.element("willpower"));
      IHealthCost healthCost = healthCostBuilder.buildCost(costListElement.element("health"));
      Cost xpCost = costBuilder.buildCost(costListElement.element("experience"));
      return new CostList(essenceCost, willpowerCost, healthCost, xpCost);
    } catch (Exception e) {
      return new CostList(null, null, null, null);
    }
  }
}