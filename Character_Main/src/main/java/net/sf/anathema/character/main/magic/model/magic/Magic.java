package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.model.magic.cost.ICostList;
import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public interface Magic extends Identifier {

  MagicAttribute[] getAttributes();

  IExaltedSourceBook[] getSources();

  IExaltedSourceBook getPrimarySource();

  ICostList getTemporaryCost();

  boolean isFavored(Hero hero);

  boolean hasAttribute(Identifier attribute);

  String getAttributeValue(Identifier attribute);
}