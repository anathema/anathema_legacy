package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;

public interface ICharmGroup extends Identified {

  ICharm[] getAllCharms();

  ICharacterType getCharacterType();

  boolean isMartialArtsGroup();
}