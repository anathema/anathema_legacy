package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;

public class ProxyCharmLearnStrategy implements ICharmLearnStrategy {

  private ICharmLearnStrategy strategy;

  public ProxyCharmLearnStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm) {
    return strategy.isUnlearnable(group, charm);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, ICharm charm) {
    return strategy.isLearned(group, charm);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, ICharm charm) {
    strategy.toggleLearned(group, charm);
  }
}