package net.sf.anathema.hero.traits.model;

public class GrumpyIncrementChecker implements IncrementChecker {

  @Override
  public final boolean isValidIncrement(int increment) {
    return false;
  }
}