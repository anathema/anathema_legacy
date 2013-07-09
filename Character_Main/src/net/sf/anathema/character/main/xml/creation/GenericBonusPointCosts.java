package net.sf.anathema.character.main.xml.creation;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;
import net.sf.anathema.character.main.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.xml.creation.magic.CharmKeywordCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.lang.clone.ICloneable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Map;

public class GenericBonusPointCosts extends ReflectionEqualsObject implements BonusPointCosts, ICloneable<GenericBonusPointCosts>, Serializable {

  private int generalAbilityCost = 0;
  private int favoredAbilityCost = 0;
  private int generalCharmCost = 0;
  private int generalHighLevelMartialArtsCharmCost = 0;
  private int favoredCharmCost = 0;
  private int favoredHighLevelMartialArtsCharmCost = 0;
  private MartialArtsLevel standardLevel;
  private CurrentRatingCosts essenceCost = new FixedValueRatingCosts(0);
  private int willpowerCost = 0;
  private CurrentRatingCosts virtueCost = new FixedValueRatingCosts(0);
  private int favoredSpecialtyDotsPerBonusPoint = 0;
  private int generalSpecialtyDotsPerBonusPoint = 0;
  private int generalAttributeCost = 0;
  private int favoredAttributeCost = 0;
  private int maximumFreeVirtueRank = 3;
  private int maximumFreeAbilityRank = 3;
  private CharmKeywordCosts generalKeywordCosts = new CharmKeywordCosts();
  private CharmKeywordCosts favoredKeywordCosts = new CharmKeywordCosts();

  @Override
  public int getCharmCosts(Charm charm, ICostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(charm);
    CharmKeywordCosts set = favored ? favoredKeywordCosts : generalKeywordCosts;
    if (set.hasCostFor(charm.getAttributes())) {
      return set.getCostFor(charm.getAttributes());
    }
    return getCharmCosts(favored, analyzer.getMartialArtsLevel(charm));
  }

  @Override
  public int getSpellCosts(ICostAnalyzer costMapping) {
    boolean isSorceryFavored = costMapping.isOccultFavored();
    return getCharmCosts(isSorceryFavored, null);
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel martialArtsLevel) {
    if (martialArtsLevel != null && (standardLevel.compareTo(martialArtsLevel) < 0 || martialArtsLevel == MartialArtsLevel.Sidereal)) {
      return favored ? favoredHighLevelMartialArtsCharmCost : generalHighLevelMartialArtsCharmCost;
    }
    return favored ? favoredCharmCost : generalCharmCost;
  }

  @Override
  public int getAttributeCosts(ValuedTraitType trait) {
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
    return virtueCost;
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
  public CurrentRatingCosts getEssenceCost() {
    return essenceCost;
  }

  @Override
  public int getMagicCosts(Magic magic, final ICostAnalyzer analyzer) {
    final int[] cost = new int[1];
    magic.accept(new IMagicVisitor() {
      @Override
      public void visitCharm(Charm charm) {
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

  public void setVirtueCosts(CurrentRatingCosts costs) {
    this.virtueCost = costs;
  }

  public void setWillpowerCosts(int willpowerCost) {
    this.willpowerCost = willpowerCost;
  }

  public void setEssenceCosts(CurrentRatingCosts costs) {
    this.essenceCost = costs;
  }

  public void setCharmCosts(int generalCharmCost, int favoredCharmCost, int generalHighLevelMartialArtsCost, int favoredHighLevelMartialArtsCost,
                            Map<String, Integer> generalKeywordCosts, Map<String, Integer> favoredKeywordCosts) {
    this.generalCharmCost = generalCharmCost;
    this.generalHighLevelMartialArtsCharmCost = generalHighLevelMartialArtsCost;
    this.favoredCharmCost = favoredCharmCost;
    this.favoredHighLevelMartialArtsCharmCost = favoredHighLevelMartialArtsCost;
    this.generalKeywordCosts.setKeywordCosts(generalKeywordCosts);
    this.favoredKeywordCosts.setKeywordCosts(favoredKeywordCosts);
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

  @Override
  public GenericBonusPointCosts clone() {
    GenericBonusPointCosts clone = SerializationUtils.clone(this);
    return clone;
  }
}