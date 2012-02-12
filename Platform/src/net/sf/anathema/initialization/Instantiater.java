package net.sf.anathema.initialization;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface Instantiater {
  @SuppressWarnings("unchecked")
  <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation) throws InitializationException;

  <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object parameter) throws InitializationException;
}
