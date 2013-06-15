package net.sf.anathema.character.generic.template.creation;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface BonusPointCosts extends AbilityPointCosts, BackgroundCreationPointCosts {

  int getCharmCosts(ICharm charm, ICostAnalyzer analyzer);

  int getAttributeCosts(IGenericTrait trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  int getSpellCosts(ICostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCost();

  int getMagicCosts(IMagic magic, ICostAnalyzer analyzer);

  int getMaximumFreeVirtueRank();
}