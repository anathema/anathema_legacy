package net.sf.anathema.character.library.trait.favorable;

public class GrumpyIncrementChecker implements IIncrementChecker {

  @Override
  public final boolean isValidIncrement(int increment) {
    return false;
  }
}