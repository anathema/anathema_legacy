package net.sf.anathema.character.generic.framework;

import com.google.common.collect.Sets;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

public class DefaultCharacterReflections implements CharacterReflections {

  private Reflections reflections;

  public DefaultCharacterReflections() {
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(CharacterModule.class, false);
  }

  private ConfigurationBuilder createConfiguration() {
    final String[] prefixes = new String[]{"net.sf.anathema.character"};
    return new ConfigurationBuilder() {
      {
        for (String prefix : prefixes) {
          addUrls(ClasspathHelper.forPackage(prefix, getContextClassLoader(), getStaticClassLoader()));
          addUrls(forPackagesInIde(prefix, getContextClassLoader(), getStaticClassLoader()));
        }
        FilterBuilder prefixFilter = new FilterBuilder();
        for (String prefix : prefixes) {
          prefixFilter.include(FilterBuilder.prefix(prefix));
        }
        filterInputsBy(prefixFilter);
        setScanners(new Scanner[]{new TypeAnnotationsScanner()});
      }
    };
  }

  private ClassLoader getContextClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  private ClassLoader getStaticClassLoader() {
    return Reflections.class.getClassLoader();
  }

  public Set<URL> forPackagesInIde(String name, ClassLoader... classLoaders) {
    final Set<URL> result = Sets.newHashSet();
    final String resourceName = name.replace(".", "/");
    String encodedResourceName = createUrlCompareString(resourceName);
    for (ClassLoader classLoader : classLoaders) {
      try {
        final Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          final URL url = urls.nextElement();
          String urlCompareString = createUrlCompareString(url.toExternalForm());
          int index = urlCompareString.lastIndexOf(encodedResourceName);
          if (index != -1) {
            result.add(new URL(urlCompareString.substring(0, index)));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  private String createUrlCompareString(String partialUrlsString) {
    return ParseUtil.encodePath(partialUrlsString);
  }
}