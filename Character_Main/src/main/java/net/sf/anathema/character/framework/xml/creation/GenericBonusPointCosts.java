package net.sf.anathema.character.framework.xml.creation;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.lang.clone.ICloneable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class GenericBonusPointCosts extends ReflectionEqualsObject implements BonusPointCosts, ICloneable<GenericBonusPointCosts>, Serializable {

  private int generalAbilityCost = 0;
  private int favoredAbilityCost = 0;
  private CurrentRatingCosts essenceCost = new FixedValueRatingCosts(0);
  private int willpowerCost = 0;
  private int favoredSpecialtyDotsPerBonusPoint = 0;
  private int generalSpecialtyDotsPerBonusPoint = 0;
  private int generalAttributeCost = 0;
  private int favoredAttributeCost = 0;
  private int maximumFreeAbilityRank = 3;

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

  public void setWillpowerCosts(int willpowerCost) {
    this.willpowerCost = willpowerCost;
  }

  public void setEssenceCosts(CurrentRatingCosts costs) {
    this.essenceCost = costs;
  }

  public void setAbilityCosts(int generalCost, int favoredCost) {
    this.generalAbilityCost = generalCost;
    this.favoredAbilityCost = favoredCost;
  }

  public void setMaximumFreeAbilityRank(int rank) {
    this.maximumFreeAbilityRank = rank;
  }

  @Override
  public GenericBonusPointCosts clone() {
    return SerializationUtils.clone(this);
  }
}