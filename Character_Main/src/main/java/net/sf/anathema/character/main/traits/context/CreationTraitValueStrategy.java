package net.sf.anathema.character.main.traits.context;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.lib.control.IntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class CreationTraitValueStrategy implements TraitValueStrategy {

  @Override
  public int getCurrentValue(Trait trait) {
    return trait.getCreationValue();
  }

  @Override
  public int getMinimalValue(Trait trait) {
    return trait.getAbsoluteMinValue();
  }

  @Override
  public void setValue(Trait trait, int value) {
    trait.setCreationValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IntValueChangedListener> creationValueControl) {
    creationValueControl.announce().valueChanged(value);
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IntValueChangedListener> currentValueControl) {
    // throw new IllegalStateException("No changes on learn value should occur in character creation.");
  }

  @Override
  public void resetCurrentValue(Trait trait) {
    trait.resetCreationValue();
  }

  @Override
  public int getCalculationValue(Trait trait) {
    return trait.getCreationCalculationValue();
  }
}