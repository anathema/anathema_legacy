package net.sf.anathema.hero.template.creation;

import net.sf.anathema.hero.template.experience.AbilityPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public interface BonusPointCosts extends AbilityPointCosts {

  int getAttributeCosts(ValuedTraitType trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  CurrentRatingCosts getEssenceCost();

  int getMaximumFreeVirtueRank();
}