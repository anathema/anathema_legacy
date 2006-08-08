package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class ProxyTraitValueStrategy implements ITraitValueStrategy {

  private ITraitValueStrategy strategy;

  public ProxyTraitValueStrategy(ITraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  public int getCurrentValue(IBasicTrait trait) {
    return strategy.getCurrentValue(trait);
  }

  public int getMinimalValue(IBasicTrait trait) {
    return strategy.getMinimalValue(trait);
  }

  public void setValue(IModifiableBasicTrait trait, int value) {
    strategy.setValue(trait, value);
  }

  public void notifyOnCreationValueChange(int value, IntValueControl currentValueControl) {
    strategy.notifyOnCreationValueChange(value, currentValueControl);
  }

  public void setStrategy(ITraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl) {
    strategy.notifyOnLearnedValueChange(value, currentValueControl);
  }

  public void resetCurrentValue(IModifiableBasicTrait trait) {
    strategy.resetCurrentValue(trait);
  }

  public int getCalculationValue(IModifiableBasicTrait trait) {
    return strategy.getCalculationValue(trait);
  }
}