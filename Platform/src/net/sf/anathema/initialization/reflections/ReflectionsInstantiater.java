package net.sf.anathema.initialization.reflections;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.initialization.InitializationException;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class ReflectionsInstantiater {

  private AnathemaReflections reflections;

  public ReflectionsInstantiater(AnathemaReflections reflections) {
    this.reflections = reflections;
  }

  @SuppressWarnings("unchecked")
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation) throws InitializationException {
    Set<Class<?>> pluginClasses = reflections.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new Function<Class<?>, T>() {
      @Override
      public T apply(Class<?> input) {
        try {
          return (T) input.newInstance();
        } catch (InstantiationException e) {
          throw new RuntimeException("Failed to load class.", e);
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Failed to load class.", e);
        }
      }
    });
  }
}