package net.sf.anathema.hero.magic.sheet.content.stats;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.lib.resources.Resources;

public class MultipleEffectCharmStats extends AbstractCharmStats implements IMagicStats {

  private final String effect;

  public MultipleEffectCharmStats(Charm charm, String effect) {
    super(charm);
    this.effect = effect;
  }

  @Override
  public String getNameString(Resources resources) {
    String effectString = resources.getString(getMagic().getId() + ".Subeffects." + effect);
    return resources.getString(getMagic().getId() + ".PrintPattern", effectString);
  }
}
