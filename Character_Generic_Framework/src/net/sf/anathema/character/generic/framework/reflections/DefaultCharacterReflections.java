package net.sf.anathema.character.generic.framework.reflections;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

public class DefaultCharacterReflections implements CharacterReflections {

  private Reflections reflections;

  public DefaultCharacterReflections() {
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation, false);
  }

  private ConfigurationBuilder createConfiguration() {
    String[] prefixes = new String[]{"net.sf.anathema.character"};
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