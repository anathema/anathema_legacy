package net.sf.anathema;

import java.io.File;
import java.lang.reflect.Method;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    EasyLoader loader = new EasyLoader(new File("./lib"));
    Class<?> aClass = loader.loadClass("net.sf.anathema.Anathema");
    Object instance = aClass.newInstance();
    Method method = aClass.getMethod("startApplication");
    method.invoke(instance);
  }
}