package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SubViewMap implements SubViewRegistry {

  private final Map<Class, SubViewFactory> factories = new HashMap<>();

  public SubViewMap(ObjectFactory objectFactory) {
    Collection<SubViewFactory> discoveredFactories = objectFactory.instantiateAll(RegisteredCharacterView.class);
    for (SubViewFactory factory : discoveredFactories) {
      Class producedClass = factory.getClass().getAnnotation(RegisteredCharacterView.class).value();
      factories.put(producedClass, factory);
    }
  }

  public <T> T get(Class<T> viewClass, CharacterType type){
    return factories.get(viewClass).create(type);
  }
}
