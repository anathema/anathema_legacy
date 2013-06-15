package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public interface ITraitValueStrategy {

  int getMinimalValue(LearnTrait trait);

  int getCurrentValue(LearnTrait trait);

  void setValue(LearnTrait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void resetCurrentValue(LearnTrait trait);

  int getCalculationValue(LearnTrait trait);
}