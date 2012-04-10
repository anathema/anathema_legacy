package net.sf.anathema.framework.value;

public class MarkerIntValueDisplayFactory implements IIntValueDisplayFactory {

  private final IntValueDisplayGraphics graphics;

  public MarkerIntValueDisplayFactory(IntValueDisplayGraphics graphics) {
    this.graphics = graphics;
  }

  @Override
  public IIntValueDisplay createIntValueDisplay(int maxValue, int value, TwoUpperBounds bounds) {
    IIntValueDisplay intValueDisplay = IntValueDisplay.createMarkerDisplay(maxValue, bounds, graphics);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}