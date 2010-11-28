package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

public class ProxyComboLearnStrategy implements IComboLearnStrategy {

  private IComboLearnStrategy strategy;

  public ProxyComboLearnStrategy(IComboLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(IComboLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public ICombo[] getCurrentCombos(IComboConfiguration configuration) {
    return strategy.getCurrentCombos(configuration);
  }

  public void finalizeCombo(IComboConfiguration configuration) {
    strategy.finalizeCombo(configuration);
  }
}