package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface BonusPointCosts extends AbilityPointCosts {

  int getCharmCosts(ICharm charm, ICostAnalyzer analyzer);

  int getAttributeCosts(ValuedTraitType trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  int getSpellCosts(ICostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCost();

  int getMagicCosts(IMagic magic, ICostAnalyzer analyzer);

  int getMaximumFreeVirtueRank();
}