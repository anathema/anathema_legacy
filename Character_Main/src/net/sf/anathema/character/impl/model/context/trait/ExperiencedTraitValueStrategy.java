package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class ExperiencedTraitValueStrategy implements ITraitValueStrategy {

  @Override
  public int getMinimalValue(Trait trait) {
    return trait.isLowerable() ? trait.getAbsoluteMinValue() : trait.getCreationValue();
  }

  @Override
  public int getCurrentValue(Trait trait) {
    if (trait.getExperiencedValue() == ITraitRules.UNEXPERIENCED) {
      return trait.getCreationValue();
    }
    return trait.getExperiencedValue();
  }

  @Override
  public void setValue(Trait trait, int value) {
    trait.setExperiencedValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl) {
    // throw new IllegalStateException("No changes on creation value should occur in experienced mode.");
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl) {
    currentValueControl.announce().valueChanged(value);
  }

  @Override
  public void resetCurrentValue(Trait trait) {
    trait.resetExperiencedValue();
  }

  @Override
  public int getCalculationValue(Trait trait) {
    return trait.getExperiencedCalculationValue();
  }
}