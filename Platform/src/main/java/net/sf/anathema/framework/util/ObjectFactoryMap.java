package net.sf.anathema.framework.util;

import net.sf.anathema.framework.environment.ObjectFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactoryMap<FACTORYTYPE> {

  private final Map<Class, FACTORYTYPE> factories = new HashMap<>();

  public ObjectFactoryMap(ObjectFactory objectFactory, Class<FACTORYTYPE> factoryType, Object... parameters) {
    Collection<FACTORYTYPE> discoveredFactories = objectFactory.instantiateAllImplementers(factoryType, parameters);
    for (FACTORYTYPE factory : discoveredFactories) {
      Class producedClass = factory.getClass().getAnnotation(Produces.class).value();
      factories.put(producedClass, factory);
    }
  }

  public FACTORYTYPE get(Class producedClass){
    return factories.get(producedClass);
  }
}