package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.type.CharacterType;

public class CharmMother {

  public static Charm createCharmForCharacterTypeFromGroup(CharacterType characterType, String groupId) {
    DummyCharm charm = new DummyCharm("ANY_ID");
    charm.setCharacterType(characterType);
    charm.setGroupId(groupId);
    return charm;
  }
}