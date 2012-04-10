package net.sf.anathema.framework.value;

public interface IIntValueDisplayFactory {

  IIntValueDisplay createIntValueDisplay(int maxValue, int value, TwoUpperBounds bounds);
}