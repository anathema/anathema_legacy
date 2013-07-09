package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface BonusPointCosts extends AbilityPointCosts {

  int getCharmCosts(Charm charm, ICostAnalyzer analyzer);

  int getAttributeCosts(ValuedTraitType trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  int getSpellCosts(ICostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCost();

  int getMagicCosts(Magic magic, ICostAnalyzer analyzer);

  int getMaximumFreeVirtueRank();
}