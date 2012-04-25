package net.sf.anathema.initialization.reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface AnnotationFinder {
  Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);
}
