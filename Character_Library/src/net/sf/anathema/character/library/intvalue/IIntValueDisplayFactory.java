package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.framework.value.IIntValueDisplay;

public interface IIntValueDisplayFactory {

  public IIntValueDisplay createIntValueDisplay(int maxValue, int value);
}