package net.sf.anathema.character.main.view;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.util.Produces;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SubViewMap implements SubViewRegistry {

  private final Map<Class, SubViewFactory> factories = new HashMap<>();

  public SubViewMap(ObjectFactory objectFactory) {
    Collection<SubViewFactory> discoveredFactories = objectFactory.instantiateAllImplementers(SubViewFactory.class);
    for (SubViewFactory factory : discoveredFactories) {
      Class producedClass = factory.getClass().getAnnotation(Produces.class).value();
      factories.put(producedClass, factory);
    }
  }

  public <T> T get(Class<T> viewClass){
    return factories.get(viewClass).create();
  }
}