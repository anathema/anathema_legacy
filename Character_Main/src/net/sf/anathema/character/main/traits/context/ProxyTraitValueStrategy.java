package net.sf.anathema.character.main.traits.context;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.lib.control.IntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class ProxyTraitValueStrategy implements TraitValueStrategy {

  private TraitValueStrategy strategy;

  public ProxyTraitValueStrategy(TraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public int getCurrentValue(Trait trait) {
    return strategy.getCurrentValue(trait);
  }

  @Override
  public int getMinimalValue(Trait trait) {
    return strategy.getMinimalValue(trait);
  }

  @Override
  public void setValue(Trait trait, int value) {
    strategy.setValue(trait, value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IntValueChangedListener> currentValueControl) {
    strategy.notifyOnCreationValueChange(value, currentValueControl);
  }

  public void setStrategy(TraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IntValueChangedListener> currentValueControl) {
    strategy.notifyOnLearnedValueChange(value, currentValueControl);
  }

  @Override
  public void resetCurrentValue(Trait trait) {
    strategy.resetCurrentValue(trait);
  }

  @Override
  public int getCalculationValue(Trait trait) {
    return strategy.getCalculationValue(trait);
  }
}