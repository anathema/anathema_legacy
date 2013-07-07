package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;

public interface ICharmInfoStringBuilder {
  String getInfoString(ICharm charm, ISpecialCharm special);
}