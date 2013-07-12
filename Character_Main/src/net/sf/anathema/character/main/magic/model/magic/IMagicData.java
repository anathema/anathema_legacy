package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.model.magic.cost.ICostList;
import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.lib.util.Identifier;

public interface IMagicData extends Identifier {

  MagicAttribute[] getAttributes();

  IExaltedSourceBook[] getSources();
  
  IExaltedSourceBook getPrimarySource();

  ICostList getTemporaryCost();
}