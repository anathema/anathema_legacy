package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class ExperiencedTraitValueStrategy implements ITraitValueStrategy {

  public int getMinimalValue(IModifiableBasicTrait trait) {
    return trait.isLowerable() ? trait.getAbsoluteMinValue() : trait.getCreationValue();
  }

  public int getCurrentValue(IModifiableBasicTrait trait) {
    if (trait.getExperiencedValue() == ITraitRules.UNEXPERIENCED) {
      return trait.getCreationValue();
    }
    return trait.getExperiencedValue();
  }

  public void setValue(IModifiableBasicTrait trait, int value) {
    trait.setExperiencedValue(value);
  }

  public void notifyOnCreationValueChange(int value, IntValueControl currentValueControl) {
    // throw new IllegalStateException("No changes on creation value should occur in experienced mode."); //$NON-NLS-1$
  }

  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl) {
    currentValueControl.fireValueChangedEvent(value);
  }

  public void resetCurrentValue(IModifiableBasicTrait trait) {
    trait.resetExperiencedValue();
  }

  public int getCalculationValue(IModifiableBasicTrait trait) {
    return trait.getExperiencedCalculationValue();
  }
}