package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckCharmPointsFixture extends AbstractCheckPointsFixture {

  public int getFavoredPicksSpent() {
    return createManagement().getFavoredCharmModel().getValue();
  }

  public int getGeneralPicksSpent() {
    return createManagement().getDefaultCharmModel().getValue();
  }

  public int getBonusPointsSpent() {
    return createManagement().getDefaultCharmModel().getSpentBonusPoints();
  }

  public int getAdditionalPicksGranted() {
    return createManagement().getDefaultCharmModel().getAdditionalRestrictedAlotment();
  }

  public int getFavoredPicksGranted() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getFavoredCreationCharmCount();
  }

  public int getGeneralPicksGranted() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getDefaultCreationCharmCount();
  }
}