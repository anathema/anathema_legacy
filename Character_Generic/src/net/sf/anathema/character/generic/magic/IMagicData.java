package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicData extends IIdentificate {

  IExaltedSourceBook[] getSources();
  
  IExaltedSourceBook getPrimarySource();

  ICostList getTemporaryCost();
}