package net.sf.anathema.character.impl.model.charm.test;

import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class DummyCharmUtilities {

  public final static ICharm createCharm(CharmType charmType) {
    return new DummyCharm(DurationType.Instant, charmType, new ComboRestrictions(), null);
  }

  public final static ICharm createCharm(CharmType charmType, IGenericTrait prerequisite) {
    return new DummyCharm(
        DurationType.Instant,
        charmType,
        new ComboRestrictions(),
        new IGenericTrait[] { prerequisite });
  }

  public final static ICharm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return new DummyCharm(DurationType.Instant, charmType, restrictions, null);
  }

  public final static ICharm createCharm(DurationType durationType, IComboRestrictions restrictions) {
    return new DummyCharm(durationType, CharmType.Reflexive, restrictions, null);
  }

  public final static ICharm createCharm(
      CharmType charmType,
      IComboRestrictions restrictions,
      IGenericTrait prerequisite) {
    return new DummyCharm(DurationType.Instant, charmType, restrictions, new IGenericTrait[] { prerequisite });
  }
}