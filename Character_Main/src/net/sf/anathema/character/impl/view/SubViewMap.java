package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.initialization.Instantiater;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SubViewMap implements SubViewRegistry {

  private final Map<Class, SubViewFactory> factories = new HashMap<>();

  public SubViewMap(Instantiater instantiater) {
    Collection<SubViewFactory> discoveredFactories = instantiater.instantiateAll(RegisteredCharacterView.class);
    for (SubViewFactory factory : discoveredFactories) {
      Class producedClass = factory.getClass().getAnnotation(RegisteredCharacterView.class).value();
      factories.put(producedClass, factory);
    }
  }

  public <T> T get(Class<T> viewClass, ICharacterType type){
    return factories.get(viewClass).create(type);
  }
}
