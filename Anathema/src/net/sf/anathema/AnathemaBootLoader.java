package net.sf.anathema;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
      Properties properties = new PropertiesLoader("anathema.properties").load();
      String libraryFolder = properties.getProperty("library.folder");
      EasyLoader loader = new EasyLoader(new File(libraryFolder));
      Class<?> aClass = loader.loadClass("net.sf.anathema.Anathema");
      Object instance = aClass.newInstance();
      Method method = aClass.getMethod("startApplication");
      method.invoke(instance);
  }
}