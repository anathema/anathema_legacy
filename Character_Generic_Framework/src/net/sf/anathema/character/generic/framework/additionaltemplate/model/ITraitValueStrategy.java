package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.lib.control.intvalue.IntValueControl;

public interface ITraitValueStrategy {

  int getMinimalValue(IBasicTrait trait);

  int getCurrentValue(IBasicTrait trait);

  void setValue(IModifiableBasicTrait trait, int value);

  void notifyOnCreationValueChange(int value, IntValueControl currentValueControl);

  void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl);

  void resetCurrentValue(IModifiableBasicTrait trait);

  int getCalculationValue(IModifiableBasicTrait trait);
}