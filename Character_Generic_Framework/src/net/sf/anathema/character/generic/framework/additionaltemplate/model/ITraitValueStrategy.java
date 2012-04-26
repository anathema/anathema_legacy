package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public interface ITraitValueStrategy {

  int getMinimalValue(IBasicTrait trait);

  int getCurrentValue(IBasicTrait trait);

  void setValue(IModifiableBasicTrait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void resetCurrentValue(IModifiableBasicTrait trait);

  int getCalculationValue(IModifiableBasicTrait trait);
}