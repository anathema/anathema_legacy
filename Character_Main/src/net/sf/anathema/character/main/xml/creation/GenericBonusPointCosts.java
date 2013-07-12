package net.sf.anathema.character.main.xml.creation;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.lang.clone.ICloneable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class GenericBonusPointCosts extends ReflectionEqualsObject implements BonusPointCosts, ICloneable<GenericBonusPointCosts>, Serializable {

  private int generalAbilityCost = 0;
  private int favoredAbilityCost = 0;
  private CurrentRatingCosts essenceCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts virtueCost = new FixedValueRatingCosts(0);
  private int willpowerCost = 0;
  private int favoredSpecialtyDotsPerBonusPoint = 0;
  private int generalSpecialtyDotsPerBonusPoint = 0;
  private int generalAttributeCost = 0;
  private int favoredAttributeCost = 0;
  private int maximumFreeVirtueRank = 3;
  private int maximumFreeAbilityRank = 3;
  public MagicCreationCostsTto charmCosts;

  @Override
  public MagicCreationCostsTto getMagicCosts() {
    return charmCosts;
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

  public void setCharmCosts(MagicCreationCostsTto charmCosts) {
    this.charmCosts = charmCosts;
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

  @Override
  public GenericBonusPointCosts clone() {
    return SerializationUtils.clone(this);
  }
}