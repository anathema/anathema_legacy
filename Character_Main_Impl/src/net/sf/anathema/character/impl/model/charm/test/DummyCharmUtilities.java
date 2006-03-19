package net.sf.anathema.character.impl.model.charm.test;

import net.sf.anathema.character.generic.impl.magic.test.DummyMartialArtsCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class DummyCharmUtilities {

  public final static ICharm createCharm(CharmType charmType) {
    return new DummyMartialArtsCharm(DurationType.Instant, charmType);
  }

  public final static ICharm createCharm(CharmType charmType, IGenericTrait prerequisite) {
    return new DummyMartialArtsCharm(
        DurationType.Instant,
        charmType,
        new ComboRestrictions(),
        new IGenericTrait[] { prerequisite });
  }

  public final static ICharm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return new DummyMartialArtsCharm(DurationType.Instant, charmType, restrictions);
  }

  public final static ICharm createCharm(DurationType durationType, IComboRestrictions restrictions) {
    return new DummyMartialArtsCharm(durationType, CharmType.Reflexive, restrictions);
  }

}