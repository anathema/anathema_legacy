package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmGroup extends Identifier {

  Charm[] getAllCharms();

  CharacterType getCharacterType();

  boolean isMartialArtsGroup();

  boolean isCharmFromGroup(Charm charm);
}