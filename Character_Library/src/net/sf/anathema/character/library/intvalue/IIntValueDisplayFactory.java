package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.framework.value.IIntValueDisplay;

public interface IIntValueDisplayFactory {

  IIntValueDisplay createIntValueDisplay(int maxValue, int value, TwoUpperBounds bounds);
}