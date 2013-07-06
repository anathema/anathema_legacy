package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.hero.points.HeroBonusPointCalculator;

public interface IAbilityCostCalculator extends HeroBonusPointCalculator {

  int getFreePointsSpent(boolean favored);

  int getFavoredPicksSpent();
}