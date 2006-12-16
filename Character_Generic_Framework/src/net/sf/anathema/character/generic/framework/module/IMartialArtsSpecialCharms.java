package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.impl.magic.charm.special.MultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;

public interface IMartialArtsSpecialCharms {

  public static IMultipleEffectCharm TYPE_EXALT_WAYS = new MultipleEffectCharm("Sidereal.(Type)ExaltWays", //$NON-NLS-1$
      new String[] { "Dawn", //$NON-NLS-1$
          "Zenith", //$NON-NLS-1$
          "Twilight", //$NON-NLS-1$
          "Night", //$NON-NLS-1$
          "Eclipse", //$NON-NLS-1$
          "Air", //$NON-NLS-1$
          "Earth", //$NON-NLS-1$
          "Fire", //$NON-NLS-1$
          "Water", //$NON-NLS-1$
          "Wood", //$NON-NLS-1$
          "FullMoon", //$NON-NLS-1$
          "ChangingMoon", //$NON-NLS-1$
          "NoMoon", //$NON-NLS-1$
          "Dusk", //$NON-NLS-1$
          "Midnight", //$NON-NLS-1$
          "Daybreak", //$NON-NLS-1$
          "Day", //$NON-NLS-1$
          "Moonshadow", //$NON-NLS-1$
          "Journeys", //$NON-NLS-1$
          "Serenity", //$NON-NLS-1$
          "Battles", //$NON-NLS-1$
          "Secrets", //$NON-NLS-1$
          "Endings" }); //$NON-NLS-1$
}