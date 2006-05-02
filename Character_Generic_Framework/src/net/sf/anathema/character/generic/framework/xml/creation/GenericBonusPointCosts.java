package net.sf.anathema.character.generic.framework.xml.creation;

import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.impl.template.points.ThresholdRatingCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericBonusPointCosts extends ReflectionCloneableObject implements IBonusPointCosts {

  private int generalAbilityCost = 0;
  private int favoredAbilityCost = 0;
  private int generalCharmCost = 0;
  private int favoredCharmCost = 0;
  private int essenceCost = 0;
  private int willpowerCost = 0;
  private int virtueCost = 0;
  private int highBackgroundCost = 0;
  private int lowBackgroundCost = 0;
  private int favoredSpecialtyDotsPerBonusPoint = 0;
  private int generalSpecialtyDotsPerBonusPoint = 0;
  private int generalAttributeCost = 0;
  private int favoredAttributeCost = 0;
  private int maximumFreeVirtueRank = 3;

  public int getCharmCosts(ICharm charm, ICostAnalyzer analyzer) {
    return getCharmCosts(analyzer.isMagicFavored(charm), analyzer.getMartialArtsLevel(charm));
  }

  public ICurrentRatingCosts getAttributeCosts(boolean favored) {
    return getFavorableFixedRatingCost(favored, favoredAttributeCost, generalAttributeCost);
  }

  private ICurrentRatingCosts getFavorableFixedRatingCost(boolean favored, int favoredCost, int generalCost) {
    if (favored) {
      return new FixedValueRatingCosts(favoredCost);
    }
    return new FixedValueRatingCosts(generalCost);
  }

  public ICurrentRatingCosts getVirtueCosts() {
    return new FixedValueRatingCosts(virtueCost);
  }

  public int getMaximumFreeVirtueRank() {
    return maximumFreeVirtueRank;
  }

  public int getWillpowerCosts() {
    return willpowerCost;
  }

  public int getSpellCosts(ICostAnalyzer costMapping) {
    boolean isSorceryFavored = costMapping.isOccultFavored();
    return getCharmCosts(isSorceryFavored, null);
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel martialArtsLevel) {
    if (martialArtsLevel == null) {
      return favored ? favoredCharmCost : generalCharmCost;
    }
    if (martialArtsLevel.compareTo(MartialArtsLevel.Sidereal) < 0) {
      return favored ? favoredCharmCost : generalCharmCost;
    }
    throw new IllegalArgumentException("Sidereal Martial Arts shan't be learned at Character Creation!"); //$NON-NLS-1$
  }

  public int getEssenceCost() {
    return essenceCost;
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

  public ICurrentRatingCosts getAbilityCosts(boolean favored) {
    return getFavorableFixedRatingCost(favored, favoredAbilityCost, generalAbilityCost);
  }

  public int getDefaultSpecialtyDotsPerPoint() {
    return generalSpecialtyDotsPerBonusPoint;
  }

  public int getFavoredSpecialtyDotsPerPoint() {
    return favoredSpecialtyDotsPerBonusPoint;
  }

  public ICurrentRatingCosts getBackgroundBonusPointCost() {
    return new ThresholdRatingCosts(lowBackgroundCost, highBackgroundCost);
  }

  public void setAttributeCost(int generalCost, int favoredCost) {
    this.generalAttributeCost = generalCost;
    this.favoredAttributeCost = favoredCost;
  }

  public void setGeneralSpecialtyDots(int generalDotsPerBonusPoint) {
    this.generalSpecialtyDotsPerBonusPoint = generalDotsPerBonusPoint;
  }

  public void setFavoredSpecialtyDots(int favoredDotsPerBonusPoint) {
    this.favoredSpecialtyDotsPerBonusPoint = favoredDotsPerBonusPoint;
  }

  public void setBackgroundCosts(int lowBackgroundCost, int highBackgroundCost) {
    this.lowBackgroundCost = lowBackgroundCost;
    this.highBackgroundCost = highBackgroundCost;
  }

  public void setVirtueCosts(int virtueCost) {
    this.virtueCost = virtueCost;
  }

  public void setWillpowerCosts(int willpowerCost) {
    this.willpowerCost = willpowerCost;
  }

  public void setEssenceCosts(int essenceCost) {
    this.essenceCost = essenceCost;
  }

  public void setCharmCosts(int generalCharmCost, int favoredCharmCost) {
    this.generalCharmCost = generalCharmCost;
    this.favoredCharmCost = favoredCharmCost;
  }

  public void setAbilityCosts(int generalCost, int favoredCost) {
    this.generalAbilityCost = generalCost;
    this.favoredAbilityCost = favoredCost;
  }

  @Override
  public GenericBonusPointCosts clone() {
    return (GenericBonusPointCosts) super.clone();
  }

  public void setMaximumFreeVirtueRank(int rank) {
    this.maximumFreeVirtueRank = rank;
  }
}