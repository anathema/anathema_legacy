package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.Identificate;

public class MartialArtsUtilities {

  private static final Identificate MARTIAL_ARTS = new Identificate(AbilityType.MartialArts.getId());

  public static boolean isMartialArtsCharm(ICharm charm) {
    return charm.hasAttribute(MARTIAL_ARTS);
  }

  public static boolean isFormCharm(ICharm charm) {
    return charm.hasAttribute(IMartialArtsCharm.FORM_ATTRIBUTE);
  }
}