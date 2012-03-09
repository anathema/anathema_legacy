package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicData extends IIdentificate {

  public IExaltedSourceBook[] getSources();
  
  public IExaltedSourceBook getPrimarySource();

  public ICostList getTemporaryCost();
}