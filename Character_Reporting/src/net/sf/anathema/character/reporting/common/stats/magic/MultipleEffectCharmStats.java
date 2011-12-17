package net.sf.anathema.character.reporting.common.stats.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.IResources;

public class MultipleEffectCharmStats extends AbstractCharmStats implements IMagicStats {

  private final String effect;

  public MultipleEffectCharmStats(ICharm charm, String effect) {
    super(charm);
    this.effect = effect;
  }

  public String getNameString(IResources resources) {
    String effectString = resources.getString(getMagic().getId() + ".Subeffects." + effect); //$NON-NLS-1$    
    return resources.getString(getMagic().getId() + ".PrintPattern", new Object[] { effectString }); //$NON-NLS-1$
  }
}
