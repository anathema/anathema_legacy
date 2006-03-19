package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicData extends IIdentificate {

  public IPermanentCostList getPermanentCost();

  public IMagicSource getSource();

  public ICostList getTemporaryCost();
}