package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.rules.IExaltedSourceBook;
import net.sf.anathema.lib.util.Identifier;

public interface IMagicData extends Identifier {

  IExaltedSourceBook[] getSources();
  
  IExaltedSourceBook getPrimarySource();

  ICostList getTemporaryCost();
}