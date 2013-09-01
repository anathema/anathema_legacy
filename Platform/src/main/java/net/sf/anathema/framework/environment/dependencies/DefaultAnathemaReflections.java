package net.sf.anathema.framework.environment.dependencies;

import com.google.common.base.Function;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.environment.resources.InternalResourceFile;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.regex.Pattern;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Sets.newHashSet;

public class DefaultAnathemaReflections implements ResourceLoader, AnnotationFinder {

  private ClassLoader[] classLoaders;
  private Reflections reflections;

  public DefaultAnathemaReflections() {
    this.classLoaders = getClassLoaders();
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  @Override
  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation, false);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    Pattern pattern = Pattern.compile(namePattern);
    return newHashSet(transform(reflections.getResources(pattern), new ToResource()));
  }

  private ConfigurationBuilder createConfiguration() {
    String[] prefixes = new String[]{"net.sf.anathema", "data"};
    return new IdeCompatibleConfiguration(prefixes, classLoaders);
  }

  private ClassLoader[] getClassLoaders() {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    ClassLoader staticClassLoader = Reflections.class.getClassLoader();
    return new ClassLoader[]{contextClassLoader, staticClassLoader};
  }

  private class ToResource implements Function<String, ResourceFile> {
    @Override
    public ResourceFile apply(String resource) {
      ClassLoader loaderForResource = null;
      for (ClassLoader loader : classLoaders) {
        if (loader.getResource(resource) != null) {
          loaderForResource = loader;
        }
      }
      return new InternalResourceFile(resource, loaderForResource);
    }
  }
}