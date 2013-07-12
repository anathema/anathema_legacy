package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmGroup extends Identifier {

  Charm[] getAllCharms();

  CharacterType getCharacterType();

  boolean isMartialArtsGroup();

  boolean isCharmFromGroup(Charm charm);
}