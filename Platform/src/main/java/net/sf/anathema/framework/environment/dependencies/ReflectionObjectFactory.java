package net.sf.anathema.framework.environment.dependencies;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.framework.environment.ObjectFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

public class ReflectionObjectFactory implements ObjectFactory {

  private AnnotationFinder finder;
  private InterfaceFinder interfaceFinder;

  public ReflectionObjectFactory(AnnotationFinder finder, InterfaceFinder interfaceFinder) {
    this.finder = finder;
    this.interfaceFinder = interfaceFinder;
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    Set<Class<?>> pluginClasses = finder.getTypesAnnotatedWith(annotation);
    List<Class<?>> sortedClasses = sort(pluginClasses);
    return Collections2.transform(sortedClasses, new Instantiate<T>(parameter));
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    Set<Class<?>> pluginClasses = finder.getTypesAnnotatedWith(annotation);
    return Collections2.transform(pluginClasses, new Instantiate<T>(parameter));
  }

  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Set<Class<?>> classes = interfaceFinder.findAll(interfaceClass);
    Collection<Class<?>>  classesToUse = filterBlackListedClasses(classes);
    return Collections2.transform(classesToUse, new Instantiate<T>(parameter));
  }

  private Collection<Class<?>>  filterBlackListedClasses(Set<Class<?>> classes) {
    return Collections2.filter(classes,new Predicate<Class<?>>() {
      @Override
      public boolean apply(Class<?> input) {
        return !input.isAnnotationPresent(DoNotUseInProduction.class);
      }
    });
  }

  private List<Class<?>> sort(Set<Class<?>> pluginClasses) {
    List<Class<?>> list = new ArrayList<>(pluginClasses);
    Collections.sort(list, new ByWeightAnnotation());
    return list;
  }

  private class Instantiate<T> implements Function<Class<?>, T> {
    private final Object[] parameters;

    public Instantiate(Object[] parameters) {
      this.parameters = parameters;
    }

    @Override
    public T apply(Class<?> input) {
      return instantiate(input);
    }

    @SuppressWarnings("unchecked")
    private <T> T instantiate(Class<?> input) {
      try {
        return (T) invokeConstructor(input, parameters);
      } catch (InstantiationException e) {
        throw new RuntimeException(format("Class {0} is abstract, but should not be.", input.getName()), e);
      } catch (IllegalAccessException e) {
        String parameterTypes[] = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
          parameterTypes[i] = parameters[i].getClass().getName();
        }
        String message = format("Class {0} declares a private constructor for {1}, but should be public.",
                input.getName(), Arrays.deepToString(parameterTypes));
        throw new RuntimeException(message, e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(format("Constructor of {0} threw an exception.", input.getName()), e);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException("Could not find a matching constructor.", e);
      }
    }
  }
}