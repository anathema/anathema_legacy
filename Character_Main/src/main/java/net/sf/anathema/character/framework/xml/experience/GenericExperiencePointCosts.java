package net.sf.anathema.character.framework.xml.experience;

import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericExperiencePointCosts extends ReflectionCloneableObject<GenericExperiencePointCosts> implements IExperiencePointCosts {

  private CurrentRatingCosts generalAttributeCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts favoredAttributeCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts generalAbilityCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts favoredAbilityCost = new FixedValueRatingCosts(0);
  private int specialtyCost = 0;
  private CurrentRatingCosts virtueCosts = new FixedValueRatingCosts(0);
  private CurrentRatingCosts willpowerCosts = new FixedValueRatingCosts(0);
  private CurrentRatingCosts essenceCosts = new FixedValueRatingCosts(0);

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    return favored ? favoredAbilityCost : generalAbilityCost;
  }

  @Override
  public CurrentRatingCosts getAttributeCosts(boolean favored) {
    return favored ? favoredAttributeCost : generalAttributeCost;
  }

  @Override
  public CurrentRatingCosts getEssenceCosts() {
    return essenceCosts;
  }

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return specialtyCost;
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return virtueCosts;
  }

  @Override
  public CurrentRatingCosts getWillpowerCosts() {
    return willpowerCosts;
  }

  public void setGeneralAttributeCosts(CurrentRatingCosts generalAttributeCost) {
    this.generalAttributeCost = generalAttributeCost;
  }

  public void setFavoredAttributeCosts(CurrentRatingCosts favoredAttributeCost) {
    this.favoredAttributeCost = favoredAttributeCost;
  }

  public void setGeneralAbilityCosts(CurrentRatingCosts generalAbilityCost) {
    this.generalAbilityCost = generalAbilityCost;
  }

  public void setFavoredAbilityCosts(CurrentRatingCosts favoredAbilityCost) {
    this.favoredAbilityCost = favoredAbilityCost;
  }

  public void setSpecialtyCosts(int specialtyCost) {
    this.specialtyCost = specialtyCost;
  }

  public void setWillpowerCosts(CurrentRatingCosts willpowerCosts) {
    this.willpowerCosts = willpowerCosts;
  }

  public void setVirtueCosts(CurrentRatingCosts virtueCosts) {
    this.virtueCosts = virtueCosts;
  }

  public void setEssenceCosts(CurrentRatingCosts essenceCosts) {
    this.essenceCosts = essenceCosts;
  }
}