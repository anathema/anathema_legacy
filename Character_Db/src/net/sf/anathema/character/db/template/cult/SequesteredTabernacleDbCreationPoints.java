package net.sf.anathema.character.db.template.cult;

public class SequesteredTabernacleDbCreationPoints extends CultDbCreationPoints {
  @Override
  public int getFavoredCreationCharmCount() {
    return 0;
  }

  @Override
  public int getDefaultCreationCharmCount() {
    return 5;
  }
}