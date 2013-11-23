package net.sf.anathema.hero.initialization;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.model.HeroModelFactory;

import java.util.Collection;

public class ModelFactoryAutoCollector implements ModelFactoryCollector {

  private ObjectFactory objectFactory;

  public ModelFactoryAutoCollector(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public Collection<HeroModelFactory> collect() {
    return objectFactory.instantiateAllImplementers(HeroModelFactory.class);
  }
}