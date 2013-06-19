package net.sf.anathema.hero.persistence;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.hero.initialization.ModelFactoryCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;

public class HeroModelPersisterAutoCollector implements ModelFactoryCollector {

  private ICharacterGenerics generics;

  public HeroModelPersisterAutoCollector(ICharacterGenerics generics) {
    this.generics = generics;
  }

  public Collection<HeroModelFactory> collect() {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(HeroModelPersisterCollected.class);
  }
}