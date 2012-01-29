package net.sf.anathema.initialization.reflections;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import static java.text.MessageFormat.format;

public class ReflectionsInstantiater implements Instantiater {

  private AnathemaReflections reflections;

  public ReflectionsInstantiater(AnathemaReflections reflections) {
    this.reflections = reflections;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation) throws InitializationException {
    Set<Class<?>> pluginClasses = reflections.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new Function<Class<?>, T>() {
      @Override
      public T apply(Class<?> input) {
        return instantiate(input);
      }
    });
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T, P> Collection<T> instantiateAll(Class<? extends Annotation> annotation, final Class<P> parameterClass, final P parameter) throws InitializationException {
    Set<Class<?>> pluginClasses = reflections.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new Function<Class<?>, T>() {
      @Override
      public T apply(Class<?> input) {
        return instantiate(input, parameterClass, parameter);
      }
    });
  }

  private <T> T instantiate(Class<?> input) {
    try {
      return (T) input.newInstance();
    } catch (InstantiationException e) {
      throw new RuntimeException(format("Class {0} is abstract, but should not be.", input.getName()), e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(format("Class {0} declares a private default constructor, but should be public.", input.getName()), e);
    }
  }

  private <T, P> T instantiate(Class<?> input, Class<P> parameterClass, P parameter) {
    try {
      Constructor<?> constructor = input.getConstructor(parameterClass);
      return (T) constructor.newInstance(parameter);
    } catch (InstantiationException e) {
      throw new RuntimeException(format("Class {0} is abstract, but should not be.", input.getName()), e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(format("Class {0} declares a private constructor for {1}, but should be public.", input.getName(), parameter.getClass().getName()), e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(format("Class {0} has no constructor for {1}.", input.getName(), parameter.getClass().getName()), e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(format("Constructor of {0} threw an exception.", input.getName()), e);
    }
  }
}