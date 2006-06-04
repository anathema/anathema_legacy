package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicData extends IIdentificate {

  public IPermanentCostList getPermanentCost();

  public IExaltedSourceBook getSource();

  public ICostList getTemporaryCost();
}