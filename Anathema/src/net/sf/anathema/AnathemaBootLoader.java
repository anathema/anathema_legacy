package net.sf.anathema;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Properties;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    ClassLoader loader = selectClassLoader();
    Class<?> mainClass = loadMainClass(loader);
    Object instance = mainClass.newInstance();
    Method method = mainClass.getMethod("startApplication");
    method.invoke(instance);
  }

  private static ClassLoader selectClassLoader() throws IOException {
    if (!isClasspathConfigured()) {
      return useCustomClassLoader();
    } else {
      return useSystemClassLoader();
    }
  }

  private static ClassLoader useSystemClassLoader() throws IOException {
    if (!isJavaFxOnClasspath()) {
      URL javaFxUrl = lookupJavaFx();
      addURLToSystemClassLoader(javaFxUrl);
    }
    return ClassLoader.getSystemClassLoader();
  }

  private static ClassLoader useCustomClassLoader() throws MalformedURLException {
    Properties properties = new PropertiesLoader("anathema.properties").load();
    String libraryFolder = properties.getProperty("library.folder");
    URL javaFxUrl = lookupJavaFx();
    return new EasyLoader(Paths.get(libraryFolder), javaFxUrl);
  }

  private static URL lookupJavaFx() throws MalformedURLException {
    String fxPath = System.getProperty("java.home") + "/lib/jfxrt.jar";
    System.out.println("Loading JavaFX from " + fxPath);
    return Paths.get(fxPath).toUri().toURL();
  }

  public static void addURLToSystemClassLoader(URL url) throws IOException {
    URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    Class sysclass = URLClassLoader.class;
    try {
      Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
      method.setAccessible(true);
      method.invoke(loader, new Object[]{url});
    } catch (Throwable t) {
      throw new RuntimeException("Could not add URL to system classloader: " + url.toExternalForm());
    }
  }

  private static boolean isClasspathConfigured() {
    try {
      loadMainClass(ClassLoader.getSystemClassLoader());
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private static boolean isJavaFxOnClasspath() {
    try {
      ClassLoader.getSystemClassLoader().loadClass("javafx.application.Application");
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private static Class<?> loadMainClass(ClassLoader loader) throws ClassNotFoundException {
    return loader.loadClass("net.sf.anathema.Anathema");
  }
}