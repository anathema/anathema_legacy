package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.lib.control.intvalue.IntValueControl;

public interface ITraitValueStrategy {

  public int getMinimalValue(IBasicTrait trait);

  public int getCurrentValue(IBasicTrait trait);

  public void setValue(IModifiableBasicTrait trait, int value);

  public void notifyOnCreationValueChange(int value, IntValueControl currentValueControl);

  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl);

  public void resetCurrentValue(IModifiableBasicTrait trait);

  public int getCalculationValue(IModifiableBasicTrait trait);
}