package net.sf.anathema.character.db.template.outcaste;

import net.sf.anathema.character.db.template.DbBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.points.ThresholdRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public class OutcasteBonusPointCosts extends DbBonusPointCosts {

  @Override
  public ICurrentRatingCosts getBackgroundBonusPointCost() {
    return new ThresholdRatingCosts(2, 3);
  }
}