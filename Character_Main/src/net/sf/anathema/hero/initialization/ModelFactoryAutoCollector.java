package net.sf.anathema.hero.initialization;

import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;

public class ModelFactoryAutoCollector implements ModelFactoryCollector {

  private ICharacterGenerics generics;

  public ModelFactoryAutoCollector(ICharacterGenerics generics) {
    this.generics = generics;
  }

  public Collection<HeroModelFactory> collect() {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(HeroModelAutoCollector.class);
  }
}