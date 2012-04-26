package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class ProxyTraitValueStrategy implements ITraitValueStrategy {

  private ITraitValueStrategy strategy;

  public ProxyTraitValueStrategy(ITraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public int getCurrentValue(IBasicTrait trait) {
    return strategy.getCurrentValue(trait);
  }

  @Override
  public int getMinimalValue(IBasicTrait trait) {
    return strategy.getMinimalValue(trait);
  }

  @Override
  public void setValue(IModifiableBasicTrait trait, int value) {
    strategy.setValue(trait, value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl) {
    strategy.notifyOnCreationValueChange(value, currentValueControl);
  }

  public void setStrategy(ITraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IIntValueChangedListener> currentValueControl) {
    strategy.notifyOnLearnedValueChange(value, currentValueControl);
  }

  @Override
  public void resetCurrentValue(IModifiableBasicTrait trait) {
    strategy.resetCurrentValue(trait);
  }

  @Override
  public int getCalculationValue(IModifiableBasicTrait trait) {
    return strategy.getCalculationValue(trait);
  }
}