package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.Identificate;

import java.text.MessageFormat;

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
    String message = MessageFormat.format(
            "Martial Arts Charm without level: {0}. Please ensure it has a Martial Arts level as a 'charmAttribute'.",
            charm.getId());
    throw new IllegalStateException(message); //$NON-NLS-1$
  }

  public static boolean hasLevel(MartialArtsLevel level, ICharm charm) {
    return charm.hasAttribute(new Identificate(level.name()));
  }
}