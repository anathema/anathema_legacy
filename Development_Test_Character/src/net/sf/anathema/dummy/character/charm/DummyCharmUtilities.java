package net.sf.anathema.dummy.character.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class DummyCharmUtilities {

  public final static ICharm createCharm(CharmType charmType) {
    return new DummyCharm("Instant", charmType, new ComboRestrictions(), null); //$NON-NLS-1$
  }

  public final static ICharm createCharm(CharmType charmType, IGenericTrait prerequisite) {
    return new DummyCharm("Instant", //$NON-NLS-1$
        charmType,
        new ComboRestrictions(),
        new IGenericTrait[] { prerequisite });
  }

  public final static ICharm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return new DummyCharm("Instant", charmType, restrictions, null); //$NON-NLS-1$
  }

  public final static ICharm createCharm(String duration, IComboRestrictions restrictions) {
    return new DummyCharm(duration, CharmType.Reflexive, restrictions, null);
  }

  public final static ICharm createCharm(
      CharmType charmType,
      IComboRestrictions restrictions,
      IGenericTrait prerequisite) {
    return new DummyCharm("Instant", charmType, restrictions, new IGenericTrait[] { prerequisite }); //$NON-NLS-1$
  }
}