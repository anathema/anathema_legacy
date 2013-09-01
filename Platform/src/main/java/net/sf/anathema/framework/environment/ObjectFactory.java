package net.sf.anathema.framework.environment;

import net.sf.anathema.initialization.InitializationException;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface ObjectFactory {

  <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException;

  <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException;
}