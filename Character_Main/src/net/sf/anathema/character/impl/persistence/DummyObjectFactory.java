package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

public class DummyObjectFactory implements ObjectFactory {
  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return new ArrayList<T>();
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return new ArrayList<T>();
  }
}