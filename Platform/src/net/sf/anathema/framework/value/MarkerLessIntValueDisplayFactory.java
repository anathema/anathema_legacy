package net.sf.anathema.framework.value;

public class MarkerLessIntValueDisplayFactory implements IIntValueDisplayFactory {

  private final IntValueDisplayGraphics graphics;

  public MarkerLessIntValueDisplayFactory(IntValueDisplayGraphics graphics) {
    this.graphics = graphics;
  }

  @Override
  public IIntValueDisplay createIntValueDisplay(int maxValue, int value, TwoUpperBounds bounds) {
    IIntValueDisplay intValueDisplay = IntValueDisplay.createMarkerLessDisplay(maxValue, bounds, graphics);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}