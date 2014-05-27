package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public class DummyBonusPointCosts implements BonusPointCosts {

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new FixedValueRatingCosts(1);
    }
    return new FixedValueRatingCosts(2);
  }

  @Override
  public int getAttributeCosts(ValuedTraitType trait) {
    return new FixedValueRatingCosts(4).getRatingCosts(trait.getCurrentValue());
  }

  @Override
  public int getMaximumFreeAbilityRank() {
    return 3;
  }

  @Override
  public int getWillpowerCosts() {
    return 2;
  }

  @Override
  public int getFavoredSpecialtyDotsPerPoint() {
    return 2;
  }

  @Override
  public int getDefaultSpecialtyDotsPerPoint() {
    return 1;
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return new FixedValueRatingCosts(7);
  }
}