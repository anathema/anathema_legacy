package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;

public interface ICharmInfoStringBuilder {
  String getInfoString(ICharm charm, ISpecialCharm special);
}