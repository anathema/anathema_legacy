package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class CreationTraitValueStrategy implements ITraitValueStrategy {

  public int getCurrentValue(IBasicTrait trait) {
    return trait.getCreationValue();
  }

  public int getMinimalValue(IBasicTrait trait) {
    return trait.getAbsoluteMinValue();
  }

  public void setValue(IModifiableBasicTrait trait, int value) {
    trait.setCreationValue(value);
  }

  public void notifyOnCreationValueChange(int value, IntValueControl creationValueControl) {
    creationValueControl.fireValueChangedEvent(value);
  }

  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl) {
    // throw new IllegalStateException("No changes on learn value should occur in character creation."); //$NON-NLS-1$
  }

  public void resetCurrentValue(IModifiableBasicTrait trait) {
    trait.resetCreationValue();
  }

  public int getCalculationValue(IModifiableBasicTrait trait) {
    return trait.getCreationCalculationValue();
  }
}