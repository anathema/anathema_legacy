package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.Identificate;

public class MartialArtsUtilities {

  public static final Identificate MARTIAL_ARTS = new Identificate(AbilityType.MartialArts.name());

  public static boolean isMartialArtsCharm(ICharm charm) {
    return charm.hasAttribute(MARTIAL_ARTS);
  }

  public static boolean isFormCharm(ICharm charm) {
    return charm.hasAttribute(ICharmData.FORM_ATTRIBUTE);
  }

  public static MartialArtsLevel getLevel(ICharm charm) {
    if (!isMartialArtsCharm(charm)) {
      return null;
    }
    for (MartialArtsLevel level : MartialArtsLevel.values()) {
      if (charm.hasAttribute(new Identificate(level.name()))) {
        return level;
      }
    }
    throw new IllegalStateException("Martial Arts Charm without level: " + charm.getId()); //$NON-NLS-1$
  }

  public static boolean hasLevel(MartialArtsLevel level, ICharm charm) {
    if (!isMartialArtsCharm(charm)) {
      return false;
    }
    return charm.hasAttribute(new Identificate(level.name()));
  }
}