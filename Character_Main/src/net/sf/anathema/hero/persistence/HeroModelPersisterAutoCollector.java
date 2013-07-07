package net.sf.anathema.hero.persistence;

import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;

public class HeroModelPersisterAutoCollector {

  private ICharacterGenerics generics;

  public HeroModelPersisterAutoCollector(ICharacterGenerics generics) {
    this.generics = generics;
  }

  public Collection<HeroModelPersister> collect() {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(HeroModelPersisterCollected.class);
  }
}