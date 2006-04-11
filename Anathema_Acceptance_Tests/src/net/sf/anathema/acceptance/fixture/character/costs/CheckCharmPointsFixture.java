package net.sf.anathema.acceptance.fixture.character.costs;

public class CheckCharmPointsFixture extends AbstractCheckPointsFixture {

  public int getFavoredPicksSpent() {
    return createManagement().getFavoredCharmModel().getValue();
  }

  public int getGeneralPicksSpent() {
    return createManagement().getDefaultCharmPicksSpent();
  }

  public int getBonusPointsSpent() {
    return createManagement().getCharmBonusPointsSpent();
  }

  public int getAdditionalPicksGranted() {
    return createManagement().getAdditionalMagicPointsAmount();
  }

  public int getFavoredPicksGranted() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getFavoredCreationCharmCount();
  }

  public int getGeneralPicksGranted() {
    return getCharacterStatistics().getCharacterTemplate().getCreationPoints().getDefaultCreationCharmCount();
  }
}