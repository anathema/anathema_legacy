package net.sf.anathema.character.generic.template.creation;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;

public interface IBonusPointCosts extends IAbilityPointCosts, IBackgroundCreationPointCosts {

  int getCharmCosts(ICharm charm, ICostAnalyzer analyzer);

  int getAttributeCosts(IFavorableGenericTrait trait);

  CurrentRatingCosts getVirtueCosts();

  int getWillpowerCosts();

  int getSpellCosts(ICostAnalyzer costMapping);

  int getEssenceCost();

  int getMagicCosts(IMagic magic, ICostAnalyzer analyzer);

  int getMaximumFreeVirtueRank();
}