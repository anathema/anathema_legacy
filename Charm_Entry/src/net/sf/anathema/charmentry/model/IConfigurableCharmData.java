package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;

public interface IConfigurableCharmData extends IExtendedCharmData {

  public String getName();

  public IConfigurablePermanentCostList getPermanentCost();

  public IConfigurableCostList getTemporaryCost();

  public ExaltedEdition getEdition();

  public CharmTypeModel getCharmTypeModel();
}