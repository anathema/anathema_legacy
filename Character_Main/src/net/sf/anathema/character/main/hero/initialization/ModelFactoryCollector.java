package net.sf.anathema.character.main.hero.initialization;

import net.sf.anathema.character.main.hero.HeroModelFactory;

import java.util.Collection;

public interface ModelFactoryCollector {
  Collection<HeroModelFactory> collect();
}