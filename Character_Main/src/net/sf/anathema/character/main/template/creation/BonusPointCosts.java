package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;

public interface BonusPointCosts extends AbilityPointCosts {

  MagicCreationCostsTto getMagicCosts();

  int getAttributeCosts(ValuedTraitType trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  CurrentRatingCosts getEssenceCost();

  int getMaximumFreeVirtueRank();
}