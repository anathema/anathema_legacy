package net.sf.anathema.framework.environment.dependencies;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface AnnotationFinder {
  Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);
}