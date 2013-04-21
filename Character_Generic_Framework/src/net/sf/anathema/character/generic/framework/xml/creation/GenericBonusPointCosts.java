package net.sf.anathema.character.generic.framework.xml.creation;

import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.impl.template.points.ThresholdRatingCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

import java.util.Map;

public class GenericBonusPointCosts extends ReflectionCloneableObject<GenericBonusPointCosts> implements
        BonusPointCosts {

  private int generalAbilityCost = 0;
  private int favoredAbilityCost = 0;
  private int generalCharmCost = 0;
  private int generalHighLevelMartialArtsCharmCost = 0;
  private int favoredCharmCost = 0;
  private int favoredHighLevelMartialArtsCharmCost = 0;
  private MartialArtsLevel standardLevel;
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
  private int maximumFreeAbilityRank = 3;
  private Map<String, Integer> generalKeywordCosts;
  private Map<String, Integer> favoredKeywordCosts;

  @Override
  public int getCharmCosts(ICharm charm, ICostAnalyzer analyzer)
  {
	  boolean favored = analyzer.isMagicFavored(charm);
	  for (ICharmAttribute attribute : charm.getAttributes())
	  {
		  Map<String, Integer> set = favored ? favoredKeywordCosts : generalKeywordCosts;
		  if (set != null && set.get(attribute.getId()) != null)
				  return set.get(attribute.getId());
	   }
	   return getCharmCosts(favored, analyzer.getMartialArtsLevel(charm));
  }

  @Override
  public int getAttributeCosts(IFavorableGenericTrait trait) {
    CurrentRatingCosts attributeCosts = getAttributeCosts(trait.isCasteOrFavored());
    return attributeCosts.getRatingCosts(trait.getCurrentValue());
  }

  private CurrentRatingCosts getAttributeCosts(boolean favored) {
    return getFavorableFixedRatingCost(favored, favoredAttributeCost, generalAttributeCost);
  }

  private CurrentRatingCosts getFavorableFixedRatingCost(boolean favored, int favoredCost, int generalCost) {
    if (favored) {
      return new FixedValueRatingCosts(favoredCost);
    }
    return new FixedValueRatingCosts(generalCost);
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return new FixedValueRatingCosts(virtueCost);
  }

  @Override
  public int getMaximumFreeVirtueRank() {
    return maximumFreeVirtueRank;
  }

  @Override
  public int getMaximumFreeAbilityRank() {
    return maximumFreeAbilityRank;
  }

  @Override
  public int getWillpowerCosts() {
    return willpowerCost;
  }

  @Override
  public int getSpellCosts(ICostAnalyzer costMapping) {
    boolean isSorceryFavored = costMapping.isOccultFavored();
    return getCharmCosts(isSorceryFavored, null);
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel martialArtsLevel) {
    if (martialArtsLevel != null && (standardLevel.compareTo(martialArtsLevel) < 0 ||
    		martialArtsLevel == MartialArtsLevel.Sidereal)) {
      return favored ? favoredHighLevelMartialArtsCharmCost : generalHighLevelMartialArtsCharmCost;
    }
    return favored ? favoredCharmCost : generalCharmCost;
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return new FixedValueRatingCosts(essenceCost);
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

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    return getFavorableFixedRatingCost(favored, favoredAbilityCost, generalAbilityCost);
  }

  @Override
  public int getDefaultSpecialtyDotsPerPoint() {
    return generalSpecialtyDotsPerBonusPoint;
  }

  @Override
  public int getFavoredSpecialtyDotsPerPoint() {
    return favoredSpecialtyDotsPerBonusPoint;
  }

  @Override
  public CurrentRatingCosts getBackgroundBonusPointCost() {
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

  public void setCharmCosts(
      int generalCharmCost,
      int favoredCharmCost,
      int generalHighLevelMartialArtsCost,
      int favoredHighLevelMartialArtsCost,
      Map<String, Integer> generalKeywordCosts,
      Map<String, Integer> favoredKeywordCosts) {
    this.generalCharmCost = generalCharmCost;
    this.generalHighLevelMartialArtsCharmCost = generalHighLevelMartialArtsCost;
    this.favoredCharmCost = favoredCharmCost;
    this.favoredHighLevelMartialArtsCharmCost = favoredHighLevelMartialArtsCost;
    this.generalKeywordCosts = generalKeywordCosts;
    this.favoredKeywordCosts = favoredKeywordCosts;
  }

  public void setAbilityCosts(int generalCost, int favoredCost) {
    this.generalAbilityCost = generalCost;
    this.favoredAbilityCost = favoredCost;
  }

  public void setMaximumFreeVirtueRank(int rank) {
    this.maximumFreeVirtueRank = rank;
  }

  public void setMaximumFreeAbilityRank(int rank) {
    this.maximumFreeAbilityRank = rank;
  }

  public void setStandardMartialArtsLevel(MartialArtsLevel standardMartialArtsLevel) {
    this.standardLevel = standardMartialArtsLevel;
  }
}