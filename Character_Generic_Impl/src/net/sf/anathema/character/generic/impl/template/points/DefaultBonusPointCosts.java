package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public class DefaultBonusPointCosts implements IBonusPointCosts {

  public int getSpellCosts(ICostAnalyzer costMapping) {
    boolean isSorceryFavored = costMapping.isOccultFavored();
    return getCharmCosts(isSorceryFavored, null);
  }

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
    throw new IllegalArgumentException("Sidereal Martial Arts shan't be learned at Character Creation!"); //$NON-NLS-1$
  }

  public ICurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new FixedValueRatingCosts(1);
    }
    return new FixedValueRatingCosts(2);
  }

  public ICurrentRatingCosts getAttributeCosts(boolean favored) {
    return new FixedValueRatingCosts(4);
  }

  public ICurrentRatingCosts getVirtueCosts() {
    return new FixedValueRatingCosts(3);
  }

  public int getMaximumFreeVirtueRank() {
    return 3;
  }

  public int getWillpowerCosts() {
    return 2;
  }

  public int getFavoredSpecialtyDotsPerPoint() {
    return 2;
  }

  public int getDefaultSpecialtyDotsPerPoint() {
    return 1;
  }

  public ICurrentRatingCosts getBackgroundBonusPointCost() {
    return new ThresholdRatingCosts(1, 2);
  }

  public int getEssenceCost() {
    return 7;
  }

  public int getMagicCosts(IMagic magic, final ICostAnalyzer analyzer) {
    final int[] cost = new int[1];
    magic.accept(new IMagicVisitor() {
      public void visitCharm(ICharm charm) {
        cost[0] = getCharmCosts(charm, analyzer);
      }

      public void visitSpell(ISpell spell) {
        cost[0] = getSpellCosts(analyzer);
      }
    });
    return cost[0];
  }
}