package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;

public interface SpiritualCreationData extends TraitListCreationData {

  int getCalculationBase(TraitType type);

  int getFreeVirtueCreationDots();

  CurrentRatingCosts getVirtueCost();

  CurrentRatingCosts getEssenceCost();

  int getWillpowerCost();

  int getMaximumFreeVirtueRank();
}
