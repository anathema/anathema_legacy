package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmGroup extends Identifier {

  ICharm[] getAllCharms();

  ICharacterType getCharacterType();

  boolean isMartialArtsGroup();

  boolean isCharmFromGroup(ICharm charm);
}