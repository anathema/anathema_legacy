package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class CheckTemplateCreationPointsFixture extends AbstractTemplateColumnFixture {

  public int getPrimaryAttributes() {
    IAttributeCreationPoints attributeCreationPoints = getCreationPoints().getAttributeCreationPoints();
    return attributeCreationPoints.getPrimaryCount();
  }

  private ICreationPoints getCreationPoints() {
    return getTemplate().getCreationPoints();
  }

  public int getSecondaryAttributes() {
    return getCreationPoints().getAttributeCreationPoints().getSecondaryCount();
  }

  public int getTertiaryAttributes() {
    return getCreationPoints().getAttributeCreationPoints().getTertiaryCount();
  }

  public int getFavoredAbilityPicks() {
    return getCreationPoints().getAbilityCreationPoints().getFavorableTraitCount();
  }

  public int getFavoredAbilityDots() {
    return getCreationPoints().getAbilityCreationPoints().getFavoredDotCount();
  }

  public int getGeneralAbilityDots() {
    return getCreationPoints().getAbilityCreationPoints().getDefaultDotCount();
  }

  public int getBackgroundDots() {
    return getCreationPoints().getBackgroundPointCount();
  }

  public int getVirtueDots() {
    return getCreationPoints().getVirtueCreationPoints();
  }

  public int getFavoredCharmPicks() {
    return getCreationPoints().getFavoredCreationCharmCount();
  }

  public int getGeneralCharmPicks() {
    return getCreationPoints().getDefaultCreationCharmCount();
  }

  public int getGeneralBonusPoints() {
    return getCreationPoints().getBonusPointCount();
  }
}