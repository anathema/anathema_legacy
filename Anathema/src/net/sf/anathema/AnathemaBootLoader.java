package net.sf.anathema;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    if (!isClasspathConfigured()) {
      Properties properties = new PropertiesLoader("anathema.properties").load();
      String libraryFolder = properties.getProperty("library.folder");
      loader = new EasyLoader(new File(libraryFolder));
    }
    Class<?> aClass = loadMainClass(loader);
    Object instance = aClass.newInstance();
    Method method = aClass.getMethod("startApplication");
    method.invoke(instance);
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