package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public class DbBonusPointCosts extends DefaultBonusPointCosts {

  @Override
  public int getEssenceCost() {
    return 10;
  }

  @Override
  protected int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level == null || level == MartialArtsLevel.Terrestrial) {
      return favored ? 5 : 7;
    }
    if (level == MartialArtsLevel.Celestial) {
      return favored ? 7 : 10;
    }
    throw new IllegalArgumentException("Terrestrial Exalts can't learn Sidereal Martial Arts"); //$NON-NLS-1$
  }
}