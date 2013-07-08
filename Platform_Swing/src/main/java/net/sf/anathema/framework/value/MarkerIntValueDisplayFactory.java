package net.sf.anathema.framework.value;

public class MarkerIntValueDisplayFactory implements IntegerViewFactory {

  private final IntValueDisplayGraphics graphics;

  public MarkerIntValueDisplayFactory(IntValueDisplayGraphics graphics) {
    this.graphics = graphics;
  }

  @Override
  public IIntValueDisplay createIntValueDisplay(int maxValue, int value) {
    IIntValueDisplay intValueDisplay = IntValueDisplay.createMarkerDisplay(maxValue, graphics);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}