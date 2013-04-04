package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.Resources;

public class MultipleEffectCharmStats extends AbstractCharmStats implements IMagicStats {

  private final String effect;

  public MultipleEffectCharmStats(ICharm charm, String effect) {
    super(charm);
    this.effect = effect;
  }

  @Override
  public String getNameString(Resources resources) {
    String effectString = resources.getString(getMagic().getId() + ".Subeffects." + effect);
    return resources.getString(getMagic().getId() + ".PrintPattern", effectString);
  }
}
