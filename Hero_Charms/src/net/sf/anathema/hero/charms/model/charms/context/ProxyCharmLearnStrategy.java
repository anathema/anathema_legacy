package net.sf.anathema.hero.charms.model.charms.context;

import net.sf.anathema.character.main.charm.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.ICharm;

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