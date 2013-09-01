package net.sf.anathema.hero.persistence;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.framework.environment.ObjectFactory;

import java.util.Collection;

public class HeroModelPersisterAutoCollector {

  private HeroEnvironment generics;

  public HeroModelPersisterAutoCollector(HeroEnvironment generics) {
    this.generics = generics;
  }

  public Collection<HeroModelPersister> collect() {
    ObjectFactory objectFactory = generics.getObjectFactory();
    return objectFactory.instantiateAll(RegisteredHeroModelPersister.class);
  }
}