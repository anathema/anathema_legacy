package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckAbilityPointsFixture extends AbstractCheckPointsFixture {

  public int getFavoredPicksSpent() {
    return createManagement().getFavoredAbilityPickModel().getValue();
  }

  public int getFavoredDotsSpent() {
    return createManagement().getFavoredAbilityModel().getValue();
  }

  public int getGeneralDotsSpent() {
    return createManagement().getDefaultAbilityModel().getValue();
  }
}