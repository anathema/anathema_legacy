package net.sf.anathema;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    if (!isClasspathConfigured()) {
      Properties properties = new PropertiesLoader("anathema.properties").load();
      String libraryFolder = properties.getProperty("library.folder");
      URL javaFxUrl = lookupJavaFx();
      loader = new EasyLoader(Paths.get(libraryFolder), javaFxUrl);
    }
    Class<?> aClass = loadMainClass(loader);
    Object instance = aClass.newInstance();
    Method method = aClass.getMethod("startApplication");
    method.invoke(instance);
  }

  private static URL lookupJavaFx() throws MalformedURLException {
    String fxPath = System.getProperty("java.home") + "/lib/jfxrt.jar";
    System.out.println("Loading JavaFX from "+fxPath);
    return Paths.get(fxPath).toUri().toURL();
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