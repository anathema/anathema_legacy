package net.sf.anathema.character.main.dummy;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;
import net.sf.anathema.character.main.magic.model.combos.ComboRestrictions;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public class DummyCharmUtilities {

  public static Charm createCharm(CharmType charmType) {
    return new DummyCharm("Instant", charmType, new ComboRestrictions(), null);
  }

  public static Charm createCharm(CharmType charmType, ValuedTraitType prerequisite) {
    return new DummyCharm("Instant", charmType, new ComboRestrictions(), new ValuedTraitType[]{prerequisite});
  }

  public static Charm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return new DummyCharm("Instant", charmType, restrictions, null);
  }

  public static Charm createCharm(String duration, IComboRestrictions restrictions) {
    return new DummyCharm(duration, CharmType.Reflexive, restrictions, null);
  }

  public static Charm createCharm(CharmType charmType, IComboRestrictions restrictions, ValuedTraitType prerequisite) {
    return new DummyCharm("Instant", charmType, restrictions, new ValuedTraitType[]{prerequisite});
  }

  public static Charm createCharm(String id, String groupId) {
    DummyCharm dummyCharm = new DummyCharm(id);
    dummyCharm.setGroupId(groupId);
    return dummyCharm;
  }
}