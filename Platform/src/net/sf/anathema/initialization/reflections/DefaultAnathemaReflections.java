package net.sf.anathema.initialization.reflections;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.regex.Pattern;

public class DefaultAnathemaReflections implements AnathemaReflections {

  private Reflections reflections;

  public DefaultAnathemaReflections() {
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  @Override
  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation, false);
  }

  @Override
  public Set<String> getResourcesMatching(String namepattern) {
    Pattern pattern = Pattern.compile(namepattern);
    return reflections.getResources(pattern);
  }

  private ConfigurationBuilder createConfiguration() {
    String[] prefixes = new String[]{"net.sf.anathema", "data"};
    return createIdeCompatibleConfiguration(prefixes);
  }

  private ConfigurationBuilder createIdeCompatibleConfiguration(String[] prefixes) {
    final ClassLoader[] classLoaders = getClassLoaders();
    return new IdeCompatibleConfiguration(prefixes, classLoaders);
  }

  private ClassLoader[] getClassLoaders() {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    ClassLoader staticClassLoader = Reflections.class.getClassLoader();
    return new ClassLoader[]{contextClassLoader, staticClassLoader};
  }
}