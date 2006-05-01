package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface IConfigurableCharmData extends IExtendedCharmData {

  public String getName();

  public IConfigurablePermanentCostList getPermanentCost();

  public IConfigurableCostList getTemporaryCost();

  public IExaltedEdition getEdition();

  public CharmTypeModel getCharmTypeModel();
}