package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class ExperiencedTraitValueStrategy implements ITraitValueStrategy {

  @Override
  public int getMinimalValue(IBasicTrait trait) {
    return trait.isLowerable() ? trait.getAbsoluteMinValue() : trait.getCreationValue();
  }

  @Override
  public int getCurrentValue(IBasicTrait trait) {
    if (trait.getExperiencedValue() == ITraitRules.UNEXPERIENCED) {
      return trait.getCreationValue();
    }
    return trait.getExperiencedValue();
  }

  @Override
  public void setValue(IModifiableBasicTrait trait, int value) {
    trait.setExperiencedValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, IntValueControl currentValueControl) {
    // throw new IllegalStateException("No changes on creation value should occur in experienced mode."); //$NON-NLS-1$
  }

  @Override
  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl) {
    currentValueControl.fireValueChangedEvent(value);
  }

  @Override
  public void resetCurrentValue(IModifiableBasicTrait trait) {
    trait.resetExperiencedValue();
  }

  @Override
  public int getCalculationValue(IModifiableBasicTrait trait) {
    return trait.getExperiencedCalculationValue();
  }
}