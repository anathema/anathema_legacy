package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckBackgroundPointsFixture extends AbstractCheckPointsFixture {

  public int getBackgroundDotsSpent() {
    return createManagement().getBackgroundModel().getValue();
  }
}