package net.sf.anathema.framework.value;

public class NullUpperBounds implements TwoUpperBounds {
  @Override
  public int getOriginalBound() {
    return 0;
  }

  @Override
  public int getModifiedBound() {
    return 0;
  }

  @Override
  public boolean haveBoundsChanged(int oldOriginalMaximum, int oldModifiedMaximum) {
    return false;
  }
}