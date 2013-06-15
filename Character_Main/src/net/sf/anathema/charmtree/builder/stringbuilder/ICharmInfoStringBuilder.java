package net.sf.anathema.charmtree.builder.stringbuilder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;

public interface ICharmInfoStringBuilder {
  String getInfoString(ICharm charm, ISpecialCharm special);
}