package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public interface ITraitValueStrategy {

  int getMinimalValue(Trait trait);

  int getCurrentValue(Trait trait);

  void setValue(Trait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl);

  void resetCurrentValue(Trait trait);

  int getCalculationValue(Trait trait);
}