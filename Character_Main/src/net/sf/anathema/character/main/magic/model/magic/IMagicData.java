package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.lib.util.Identifier;

public interface IMagicData extends Identifier {

  IExaltedSourceBook[] getSources();
  
  IExaltedSourceBook getPrimarySource();

  ICostList getTemporaryCost();
}