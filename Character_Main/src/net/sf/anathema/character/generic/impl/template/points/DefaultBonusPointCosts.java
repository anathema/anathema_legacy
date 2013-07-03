package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.ValuedTraitType;

public class DefaultBonusPointCosts implements BonusPointCosts {

  @Override
  public int getSpellCosts(ICostAnalyzer costMapping) {
    boolean isSorceryFavored = costMapping.isOccultFavored();
    return getCharmCosts(isSorceryFavored, null);
  }

  @Override
  public int getCharmCosts(ICharm charm, ICostAnalyzer costMapping) {
    return getCharmCosts(costMapping.isMagicFavored(charm), costMapping.getMartialArtsLevel(charm));
  }

  protected int getCharmCosts(boolean favored, MartialArtsLevel martialArtsLevel) {
    if (martialArtsLevel == null) {
      return favored ? 4 : 5;
    }
    if (martialArtsLevel.compareTo(MartialArtsLevel.Sidereal) < 0) {
      return favored ? 4 : 5;
    }
    throw new IllegalArgumentException("Sidereal Martial Arts shan't be learned at Hero Creation!");
  }

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new FixedValueRatingCosts(1);
    }
    return new FixedValueRatingCosts(2);
  }

  @Override
  public int getAttributeCosts(ValuedTraitType trait) {
    return new FixedValueRatingCosts(4).getRatingCosts(trait.getCurrentValue());
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return new FixedValueRatingCosts(3);
  }

  @Override
  public int getMaximumFreeVirtueRank() {
    return 3;
  }

  @Override
  public int getMaximumFreeAbilityRank() {
    return 3;
  }

  @Override
  public int getWillpowerCosts() {
    return 2;
  }

  @Override
  public int getFavoredSpecialtyDotsPerPoint() {
    return 2;
  }

  @Override
  public int getDefaultSpecialtyDotsPerPoint() {
    return 1;
  }

  @Override
  public CurrentRatingCosts getBackgroundBonusPointCost() {
    return new ThresholdRatingCosts(1, 2, 3);
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return new FixedValueRatingCosts(7);
  }

  @Override
  public int getMagicCosts(IMagic magic, final ICostAnalyzer analyzer) {
    final int[] cost = new int[1];
    magic.accept(new IMagicVisitor() {
      @Override
      public void visitCharm(ICharm charm) {
        cost[0] = getCharmCosts(charm, analyzer);
      }

      @Override
      public void visitSpell(ISpell spell) {
        cost[0] = getSpellCosts(analyzer);
      }
    });
    return cost[0];
  }
}