package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckBonusPointsFixture extends AbstractCheckPointsFixture {

  public boolean hasAdditionalPool() {
    return getCharacterStatistics().getCharacterTemplate().getAdditionalRules().getAdditionalBonusPointPools().length > 0;
  }

  public int additionalAmount() {
    return createManagement().getTotalModel().getAdditionalRestrictedAlotment();
  }

  public int additionalSpent() {
    return createManagement().getTotalModel().getAdditionalValue();
  }

  public int regularAmount() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getBonusPointCount();
  }

  public int regularSpent() {
    return createManagement().getTotalModel().getValue();
  }
}