package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmGroup extends Identifier {

  ICharm[] getAllCharms();

  ICharacterType getCharacterType();

  boolean isMartialArtsGroup();

  boolean isCharmFromGroup(ICharm charm);
}