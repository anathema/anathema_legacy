package net.sf.anathema.character.main.util;

public class NullPointModification implements IPointModification {

  @Override
  public int getAdditionalPoints(int traitValue) {
    return 0;
  }
}