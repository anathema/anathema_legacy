package net.sf.anathema.character.library.intvalue;

public interface TwoUpperBounds {

  int getOriginalBound();

  int getModifiedBound();

  boolean haveBoundsChanged(int oldOriginalMaximum, int oldModifiedMaximum);
}
