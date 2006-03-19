package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public class DbExperienceCosts extends DefaultExperienceCosts {

  @Override
  public ICurrentRatingCosts getEssenceCosts() {
    return new MultiplyRatingCosts(10);
  }

  @Override
  protected int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level == MartialArtsLevel.Celestial) {
      return favored ? 12 : 15;
    }
    if (level != MartialArtsLevel.Sidereal) {
      return favored ? 10 : 12;
    }
    throw new IllegalArgumentException("Terrestrials may not learn Sidereal level martial arts."); //$NON-NLS-1$
  }
}