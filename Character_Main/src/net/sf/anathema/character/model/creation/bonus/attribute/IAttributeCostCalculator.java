package net.sf.anathema.character.model.creation.bonus.attribute;

import net.sf.anathema.character.library.trait.FavorableTraitCost;
import net.sf.anathema.character.library.trait.Trait;

public interface IAttributeCostCalculator {

  void calculateCosts();

  int getFreePointsSpent(boolean favored);

  int getExtraFavoredDotsSpent();

  int getExtraGenericDotsSpent();

  int getBonusPointsSpent();

  int getFavoredPicksSpent();

  FavorableTraitCost[] getCosts(Trait trait);

  void calculateAttributeCosts();

  int getBonusPoints();
}