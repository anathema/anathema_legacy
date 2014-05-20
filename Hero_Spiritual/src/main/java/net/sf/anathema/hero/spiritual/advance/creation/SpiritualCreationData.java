package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.traits.TraitType;

public interface SpiritualCreationData {

  int getCalculationBase(TraitType type);

  int getFreeVirtueCreationDots();

  CurrentRatingCosts getVirtueCost();

  CurrentRatingCosts getEssenceCost();

  int getWillpowerCost();

  int getMaximumFreeVirtueRank();
}
