package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.dummy.DummyCharm;

public class CharmMother {

  public static ICharm createCharmForCharacterTypeFromGroup(ICharacterType characterType, String groupId) {
    DummyCharm charm = new DummyCharm("ANY_ID");
    charm.setCharacterType(characterType);
    charm.setGroupId(groupId);
    return charm;
  }
}