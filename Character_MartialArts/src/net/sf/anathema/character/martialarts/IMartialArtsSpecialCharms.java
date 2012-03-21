package net.sf.anathema.character.martialarts;

import net.sf.anathema.character.generic.impl.magic.charm.special.MultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;

public interface IMartialArtsSpecialCharms {

  public static final ISpecialCharm DRAGON_CLAW_ELEMENTAL_STRIKE = new MultipleEffectCharm(
      "Terrestrial.Dragon-ClawElementalStrike", //$NON-NLS-1$
      new String[] { "Air", "Earth", "Fire", "Water", "Wood" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
}
