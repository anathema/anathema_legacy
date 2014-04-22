package net.sf.anathema;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
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
    return ClassLoader.getSystemClassLoader();
  }

  private static ClassLoader useCustomClassLoader() throws MalformedURLException {
    Properties properties = new PropertiesLoader("anathema.properties").load();
    String libraryFolder = properties.getProperty("library.folder");
    return new EasyLoader(Paths.get(libraryFolder));
  }

  private static boolean isClasspathConfigured() {
    try {
      loadMainClass(ClassLoader.getSystemClassLoader());
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private static Class<?> loadMainClass(ClassLoader loader) throws ClassNotFoundException {
    return loader.loadClass("net.sf.anathema.Anathema");
  }
}