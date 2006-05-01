package net.sf.anathema.lib.data;

public class Range {
  private int high;
  private int low;
  private final boolean variable;

  public Range(int low, int high) {
    this(low, high, false);
  }

  public Range(int low, int high, boolean variable) {
    this.low = low;
    this.high = high;
    this.variable = variable;
  }

  public boolean contains(int value) {
    return ((low <= value) && (high >= value));
  }

  public boolean contains(Range range) {
    return range.getLowerBound() >= low && range.getUpperBound() <= high;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Range)) {
      return false;
    }
    Range range = (Range) object;
    return range.getLowerBound() == low && range.getUpperBound() == high;
  }

  @Override
  public int hashCode() {
    return getLowerBound() + 7 * getUpperBound();
  }

  public int getLowerBound() {
    return low;
  }

  public int getUpperBound() {
    return high;
  }

  public int getWidth() {
    return high - low + 1;
  }

  @Override
  public String toString() {
    return "[" + low + "," + high + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  public void setLowerBound(int value) {
    if (variable) {
      low = value;
    }
  }

  public void setUpperBoundBound(int value) {
    if (variable) {
      high = value;
    }
  }

  public int getNearestValue(int value) {
    if (value < getLowerBound()) {
      return getLowerBound();
    }
    if (value > getUpperBound()) {
      return getUpperBound();
    }
    return value;
  }
}