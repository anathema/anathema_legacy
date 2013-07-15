package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface BonusPointCosts extends AbilityPointCosts {

  int getAttributeCosts(ValuedTraitType trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  CurrentRatingCosts getEssenceCost();

  int getMaximumFreeVirtueRank();
}