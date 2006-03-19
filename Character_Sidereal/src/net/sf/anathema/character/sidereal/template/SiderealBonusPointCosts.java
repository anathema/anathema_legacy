package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.sidereal.colleges.model.ICollegeBonusPointCosts;

public class SiderealBonusPointCosts extends DefaultBonusPointCosts implements ICollegeBonusPointCosts {

  @Override
  public int getEssenceCost() {
    return 10;
  }

  @Override
  protected int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level == MartialArtsLevel.Sidereal) {
      return favored ? 6 : 8;
    }
    return favored ? 5 : 7;
  }

  public int getCollegeCosts(boolean favored) {
    return favored ? 6 : 8;
  }
}