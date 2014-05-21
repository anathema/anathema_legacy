package net.sf.anathema.hero.traits.model;

public class FriendlyIncrementChecker implements IncrementChecker {

  @Override
  public boolean isValidIncrement(int increment) {
    return true;
  }
}