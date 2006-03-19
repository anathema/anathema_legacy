package net.sf.anathema.character.db.template.dynastic;

public class ImmaculateDbCreationPoints extends DynasticDbCreationPoints {

  @Override
  public int getFavoredCreationCharmCount() {
    return 0;
  }

  @Override
  public int getDefaultCreationCharmCount() {
    return 5;
  }
}