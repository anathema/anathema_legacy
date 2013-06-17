package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.main.model.HeroModelFactory;

import java.util.Collection;

public interface ModelFactoryCollector {
  Collection<HeroModelFactory> collect();
}