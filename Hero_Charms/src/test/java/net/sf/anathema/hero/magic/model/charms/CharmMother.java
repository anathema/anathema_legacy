package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.dummy.DummyCharm;

public class CharmMother {

  public static Charm createCharmForCharacterTypeFromGroup(ICharacterType characterType, String groupId) {
    DummyCharm charm = new DummyCharm("ANY_ID");
    charm.setCharacterType(characterType);
    charm.setGroupId(groupId);
    return charm;
  }
}