package net.sf.anathema.character.main.library.trait.favorable;

public class GrumpyIncrementChecker implements IncrementChecker {

  @Override
  public final boolean isValidIncrement(int increment) {
    return false;
  }
}