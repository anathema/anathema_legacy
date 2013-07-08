package net.sf.anathema.framework.value;

public interface IntegerViewFactory {

  IIntValueDisplay createIntValueDisplay(int maxValue, int value);
}