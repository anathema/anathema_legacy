package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharmData;

public interface IConfigurableCharmData extends ICharmData {

  public String getName();

  public IConfigurablePermanentCostList getPermanentCost();

  public IConfigurableCostList getTemporaryCost();

  public ExaltedEdition getEdition();
}