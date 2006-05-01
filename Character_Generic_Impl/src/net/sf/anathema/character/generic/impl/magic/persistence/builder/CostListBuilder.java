package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class CostListBuilder implements ICostListBuilder {

  private static final IPermanentCostList PERMANENT_NULL_COST = new PermanentCostList(
      Cost.NULL_COST,
      Cost.NULL_COST,
      HealthCost.NULL_HEALTH_COST,
      Cost.NULL_COST);
  private final CostBuilder costBuilder = new CostBuilder();
  private final HealthCostBuilder healthCostBuilder = new HealthCostBuilder();

  public IPermanentCostList buildPermanentCostList(Element costListElement) throws PersistenceException {
    if (costListElement == null) {
      return PERMANENT_NULL_COST;
    }
    ICostList list = buildCostList(costListElement);
    ICost xpCost = costBuilder.buildCost(costListElement.element("experience")); //$NON-NLS-1$
    return new PermanentCostList(list, xpCost);
  }

  public ICostList buildTemporaryCostList(Element costListElement) throws PersistenceException {
    if (costListElement == null) {
      throw new PersistenceException("Temporary cost element required."); //$NON-NLS-1$
    }
    return buildCostList(costListElement);
  }

  private ICostList buildCostList(Element costListElement) throws PersistenceException {
    ICost essenceCost = costBuilder.buildCost(costListElement.element("essence")); //$NON-NLS-1$
    ICost willpowerCost = costBuilder.buildCost(costListElement.element("willpower")); //$NON-NLS-1$
    IHealthCost healthCost = healthCostBuilder.buildCost(costListElement.element("health")); //$NON-NLS-1$
    return new CostList(essenceCost, willpowerCost, healthCost);
  }
}