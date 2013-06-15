package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.LearnTrait;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class CreationTraitValueStrategy implements ITraitValueStrategy {

  @Override
  public int getCurrentValue(LearnTrait trait) {
    return trait.getCreationValue();
  }

  @Override
  public int getMinimalValue(LearnTrait trait) {
    return trait.getAbsoluteMinValue();
  }

  @Override
  public void setValue(LearnTrait trait, int value) {
    trait.setCreationValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> creationValueControl) {
    creationValueControl.announce().valueChanged(value);
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl) {
    // throw new IllegalStateException("No changes on learn value should occur in character creation.");
  }

  @Override
  public void resetCurrentValue(LearnTrait trait) {
    trait.resetCreationValue();
  }

  @Override
  public int getCalculationValue(LearnTrait trait) {
    return trait.getCreationCalculationValue();
  }
}