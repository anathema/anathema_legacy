package net.sf.anathema.acceptance.fixture.character.template;


public class CheckBonusPointTemplateFixture extends AbstractTemplateColumnFixture {

  public int getUnfavoredAttributePointCost() {
    return getTemplate().getBonusPointCosts().getAttributeCosts(false).getRatingCosts(1);
  }

  public int getFavoredAttributePointCost() {
    return getTemplate().getBonusPointCosts().getAttributeCosts(true).getRatingCosts(1);
  }

  public int getFavoredAbilityPointCost() {
    return getTemplate().getBonusPointCosts().getAbilityCosts(true).getRatingCosts(1);
  }

  public int getUnfavoredAbilityPointCost() {
    return getTemplate().getBonusPointCosts().getAbilityCosts(false).getRatingCosts(1);
  }

  public int getUnfavoredSpecialtyDotsPerPoint() {
    return getTemplate().getBonusPointCosts().getDefaultSpecialtyDotsPerPoint();
  }

  public int getFavoredSpecialtyDotsPerPoint() {
    return getTemplate().getBonusPointCosts().getFavoredSpecialtyDotsPerPoint();
  }

  public int getLowBackgroundCost() {
    return getTemplate().getBonusPointCosts().getBackgroundBonusPointCost().getRatingCosts(1);
  }

  public int getHighBackgroundCost() {
    return getTemplate().getBonusPointCosts().getBackgroundBonusPointCost().getRatingCosts(4);
  }

  public int getVirtueCost() {
    return getTemplate().getBonusPointCosts().getVirtueCosts().getRatingCosts(1);
  }

  public int getWillpowerCost() {
    return getTemplate().getBonusPointCosts().getWillpowerCosts();
  }

  public int getEssenceCost() {
    return getTemplate().getBonusPointCosts().getEssenceCost();
  }
}