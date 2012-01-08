package net.sf.anathema.character;

import net.sf.anathema.lib.logging.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StartupModule {

  private static final Logger logger = Logger.getLogger(StartupModule.class);

  public Set<URL> findClassPaths() {
    Set<URL> urlSet = new HashSet<URL>();
    addUrlsFromClassLoaders(urlSet);
    addUrlsFromClasspath(urlSet);
    return urlSet;
  }

  private void addUrlsFromClasspath(Set<URL> urlSet) {
    String classpath = System.getProperty("java.class.path");
    logger.info("Classpath: " + classpath);
    if (classpath == null || classpath.length() <= 0) {
      return;
    }
    try {
      URL resource = StartupModule.class.getResource("/");
      logger.info("StartupModule-Resource: " + resource);
      if (resource == null) {
        String className = StartupModule.class.getName().replace('.', '/') + ".class";
        resource = StartupModule.class.getResource(className);
        logger.info("StartupModule-Resource: " + resource);
        if (resource != null) {
          String url = resource.toExternalForm();
          url = url.substring(0, url.length() - className.length());
          resource = new URL(url);
        }
      }
      if (resource != null) {
        classpath = classpath + File.pathSeparator + new File(resource.toURI()).getAbsolutePath();
        logger.info("Extended Classpath: " + classpath);
      }
    } catch (URISyntaxException e) {
      //FIXME ignore for now
    } catch (MalformedURLException e) {
      //FIXME ignore for now
    }
    for (String path : classpath.split(File.pathSeparator)) {
      File file = new File(path);
      try {
        if (file.exists()) {
          urlSet.add(file.toURI().toURL());
        }
      } catch (MalformedURLException e) {
        logger.info("Found invalid URL in Classpath: " + path);
      }
    }
  }

  private void addUrlsFromClassLoaders(Set<URL> urlSet) {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    while (loader != null) {
      if (loader instanceof URLClassLoader) {
        URL[] urls = ((URLClassLoader) loader).getURLs();
        Collections.addAll(urlSet, urls);
      }
      loader = loader.getParent();
    }
  }
}
