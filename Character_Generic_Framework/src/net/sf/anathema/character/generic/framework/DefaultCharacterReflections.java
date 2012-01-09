package net.sf.anathema.character.generic.framework;

import com.google.common.collect.Sets;
import net.sf.anathema.character.StartupModule;
import net.sf.anathema.lib.logging.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Set;

public class DefaultCharacterReflections implements CharacterReflections {

  private static final Logger logger = Logger.getLogger(DefaultCharacterReflections.class);

  private Reflections reflections;

  public DefaultCharacterReflections() {
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation);
  }

  private ConfigurationBuilder createConfiguration() {
    final String[] prefixes = new String[]{"net.sf.anathema.character"};
    return new ConfigurationBuilder() {
      {
        Set<URL> allUrls = new StartupModule().findClassPaths();
        addUrls(allUrls);
        for (String prefix : prefixes) {
          // addUrls(ClasspathHelper.forPackage(prefix));
          addUrls(forPackagesInIde(prefix, allUrls));
        }
        FilterBuilder prefixFilter = new FilterBuilder();
        for (String prefix : prefixes) {
          prefixFilter.include(FilterBuilder.prefix(prefix));
        }
        filterInputsBy(prefixFilter);
        setScanners(new Scanner[]{new TypeAnnotationsScanner(), new CharmFileScanner()});
      }
    };
  }

  public Set<URL> forPackagesInIde(String name, Set<URL> allUrls) {
    Set<URL> result = Sets.newHashSet();
    String resourceName = name.replace(".", "/");
    logger.error("ResourceName: " + resourceName);
    String encodedResourceName = createUrlCompareString(resourceName);
    for (URL url : allUrls) {
      try {
        logger.error("External form of URL: " + url.toExternalForm());
        String urlCompareString = createUrlCompareString(url.toExternalForm());
        int index = urlCompareString.lastIndexOf(encodedResourceName);
        if (index != -1) {
          result.add(new URL(urlCompareString.substring(0, index)));
        }
      } catch (IOException e) {
        logger.error(e);
      }
    }
    return result;
  }

  private String createUrlCompareString(String partialUrlsString) {
    return ParseUtil.encodePath(partialUrlsString);
  }
}
