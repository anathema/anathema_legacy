package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public class SiderealExperienceCosts extends DefaultExperienceCosts implements ICollegeExperienceCosts {

  @Override
  public ICurrentRatingCosts getEssenceCosts() {
    return new MultiplyRatingCosts(9);
  }

  @Override
  protected int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level == null) {
      return favored ? 9 : 11;
    }
    if (level != MartialArtsLevel.Sidereal) {
      return favored ? 8 : 10;
    }
    return favored ? 10 : 12;
  }

  public ICurrentRatingCosts getCollegeExperienceCost() {
    return new MultiplyRatingCosts(3, 5);
  }
}