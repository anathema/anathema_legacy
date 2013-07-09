package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmGroup extends Identifier {

  Charm[] getAllCharms();

  ICharacterType getCharacterType();

  boolean isMartialArtsGroup();

  boolean isCharmFromGroup(Charm charm);
}