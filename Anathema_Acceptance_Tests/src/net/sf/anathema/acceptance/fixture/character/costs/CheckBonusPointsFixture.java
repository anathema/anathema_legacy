package net.sf.anathema.acceptance.fixture.character.costs;


public class CheckBonusPointsFixture extends AbstractCheckPointsFixture {

  public boolean hasAdditionalPool() {
    return getCharacterStatistics().getCharacterTemplate().getAdditionalRules().getAdditionalBonusPointPools().length > 0;
  }

  public int additionalAmount() {
    return createManagement().getAdditionalBonusPointAmount();
  }

  public int additionalSpent() {
    return createManagement().getAdditionalBonusPointSpent();
  }

  public int regularAmount() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getBonusPointCount();
  }

  public int regularSpent() {
    return createManagement().getStandardBonusPointsSpent();
  }
}