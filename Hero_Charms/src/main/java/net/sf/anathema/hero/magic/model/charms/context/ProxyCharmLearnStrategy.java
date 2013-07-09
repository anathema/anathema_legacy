package net.sf.anathema.hero.magic.model.charms.context;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charms.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnStrategy;

public class ProxyCharmLearnStrategy implements ICharmLearnStrategy {

  private ICharmLearnStrategy strategy;

  public ProxyCharmLearnStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm) {
    return strategy.isUnlearnable(group, charm);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, Charm charm) {
    return strategy.isLearned(group, charm);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, Charm charm) {
    strategy.toggleLearned(group, charm);
  }
}