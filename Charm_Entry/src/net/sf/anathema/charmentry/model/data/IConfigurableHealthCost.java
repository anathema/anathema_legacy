package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.magic.general.IHealthCost;

public interface IConfigurableHealthCost extends IConfigurableCost, IHealthCost {

  public void setType(HealthType type);
}