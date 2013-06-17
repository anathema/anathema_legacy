package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;

public class ModelFactoryAutoCollector implements ModelFactoryCollector {

  private ICharacterGenerics generics;

  public ModelFactoryAutoCollector(ICharacterGenerics generics) {
    this.generics = generics;
  }

  public Collection<HeroModelFactory> collect() {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(CharacterModelAutoCollector.class);
  }
}