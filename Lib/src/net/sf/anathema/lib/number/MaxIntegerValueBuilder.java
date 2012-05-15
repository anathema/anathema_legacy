package net.sf.anathema.lib.number;

public class MaxIntegerValueBuilder {

  private int maxValue;

  public MaxIntegerValueBuilder(int startValue) {
    this.maxValue = startValue;
  }

  public int getMaximum() {
    return maxValue;
  }

  public void add(int value) {
    maxValue = value > maxValue ? value : maxValue;
  }
}