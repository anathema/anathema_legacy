package net.sf.anathema.character.sidereal.colleges.presenter;

public interface ISiderealCollegeCreationOverview extends ISiderealCollegeOverview {

  public void setFavoredDotsOverview(int favoredPicksSpent, int favoredPicksTotal);

  public void setGeneralDotsOverview(int generalPicksSpent, int generalPicksTotal);

  public void setBonusPointsOverview(int bonusPointsSpent);
}