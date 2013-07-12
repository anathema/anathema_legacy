package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public interface Magic extends IMagicData {

  boolean isFavored(Hero hero);

  boolean hasAttribute(Identifier attribute);

  String getAttributeValue(Identifier attribute);
}