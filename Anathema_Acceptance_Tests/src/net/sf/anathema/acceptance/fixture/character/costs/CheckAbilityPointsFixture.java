package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckAbilityPointsFixture extends AbstractCheckPointsFixture {

  public int getFavoredPicksSpent() {
    return createManagement().getFavoredAbilityPicksSpent();
  }
  
  public int getFavoredDotsSpent() {
    return createManagement().getFavoredAbilityDotsSpent();
  }
  
  public int getGeneralDotsSpent() {
    return createManagement().getDefaultAbilityDotsSpent();
  }
}