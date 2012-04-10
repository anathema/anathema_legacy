package net.sf.anathema.framework.value;

public interface TwoUpperBounds {

  int getOriginalBound();

  int getModifiedBound();

  boolean haveBoundsChanged(int oldOriginalMaximum, int oldModifiedMaximum);
}
