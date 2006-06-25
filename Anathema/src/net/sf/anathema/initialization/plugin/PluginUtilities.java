package net.sf.anathema.initialization.plugin;

import java.util.Collection;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.framework.InitializationException;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class PluginUtilities {

  private PluginUtilities() {
    throw new UnreachableCodeReachedException();
  }

  @SuppressWarnings("unchecked")
  public static Collection<Parameter> getParameters(Extension extension, String parameterName) {
    return extension.getParameters(parameterName);
  }

  public static Object instantiate(Parameter classParameter) throws InitializationException {
    String className = classParameter.valueAsString();
    try {
      return Class.forName(className).newInstance();
    }
    catch (Throwable e) {
      throw new InitializationException(e);
    }
  }
}