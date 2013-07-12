package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;

public class ModelFactoryAutoCollector implements ModelFactoryCollector {

  private HeroEnvironment generics;

  public ModelFactoryAutoCollector(HeroEnvironment generics) {
    this.generics = generics;
  }

  public Collection<HeroModelFactory> collect() {
    ObjectFactory objectFactory = generics.getObjectFactory();
    return objectFactory.instantiateAll(HeroModelAutoCollector.class);
  }
}