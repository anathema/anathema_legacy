package net.sf.anathema.character.magic.basic;

import net.sf.anathema.character.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.magic.basic.cost.ICostList;
import net.sf.anathema.character.magic.basic.source.SourceBook;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public interface Magic extends Identifier {

  MagicAttribute[] getAttributes();

  SourceBook[] getSources();

  SourceBook getPrimarySource();

  ICostList getTemporaryCost();

  boolean isFavored(Hero hero);

  boolean hasAttribute(Identifier attribute);

  String getAttributeValue(Identifier attribute);
}