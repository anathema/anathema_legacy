package net.sf.anathema.initialization.reflections;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.reflect.ConstructorUtils.getMatchingAccessibleConstructor;

public class ReflectionsInstantiater implements Instantiater {

  private AnnotationFinder finder;

  public ReflectionsInstantiater(AnnotationFinder finder) {
    this.finder = finder;
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation) throws InitializationException {
    Set<Class<?>> pluginClasses = finder.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new Instantiate<T>());
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation) throws InitializationException {
    Set<Class<?>> pluginClasses = finder.getTypesAnnotatedWith(annotation);
    List<Class<?>> sortedClasses = sort(pluginClasses);
    return Collections2.transform(sortedClasses, new Instantiate<T>());
  }

  private List<Class<?>> sort(Set<Class<?>> pluginClasses) {
    List<Class<?>> list = new ArrayList<Class<?>>(pluginClasses);
    Collections.sort(list, new ByWeightAnnotation());
    return list;
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object parameter) throws InitializationException {
    Set<Class<?>> pluginClasses = finder.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new InstantiateWithParameter<T>(parameter));
  }

  @SuppressWarnings("unchecked")
  private class Instantiate<T> implements Function<Class<?>, T> {
    @Override
    public T apply(Class<?> input) {
      return (T) instantiate(input);
    }

    @SuppressWarnings("unchecked")
    private <T> T instantiate(Class<?> input) {
      try {
        return (T) input.newInstance();
      } catch (InstantiationException e) {
        throw new RuntimeException(format("Class {0} is abstract, but should not be.", input.getName()), e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(
                format("Class {0} declares a private default constructor, but should be public.", input.getName()), e);
      }
    }
  }

  private class InstantiateWithParameter<T> implements Function<Class<?>, T> {
    private final Object parameter;

    public InstantiateWithParameter(Object parameter) {
      this.parameter = parameter;
    }

    @Override
    public T apply(Class<?> input) {
      return (T) instantiate(input, parameter);
    }

    @SuppressWarnings("unchecked")
    private <T> T instantiate(Class<?> input, Object parameter) {
      try {
        Constructor<?> constructor = getMatchingAccessibleConstructor(input, parameter.getClass());
        return (T) constructor.newInstance(parameter);
      } catch (InstantiationException e) {
        throw new RuntimeException(format("Class {0} is abstract, but should not be.", input.getName()), e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(
                format("Class {0} declares a private constructor for {1}, but should be public.", input.getName(),
                        parameter.getClass().getName()), e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(format("Constructor of {0} threw an exception.", input.getName()), e);
      }
    }
  }
}