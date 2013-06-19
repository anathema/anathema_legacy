package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.model.HeroModelFactory;

import java.util.Collection;

public interface ModelFactoryCollector {
  Collection<HeroModelFactory> collect();
}