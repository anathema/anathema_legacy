package net.sf.anathema.framework.environment.resources;

import net.sf.anathema.framework.environment.Resources;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class MultiSourceStringProvider implements Resources {
  private final List<Resources> handlers = new ArrayList<>();

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return getString(key);
    }
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  private String getString(String key) {
    for (Resources handler : handlers) {
      if (handler.supportsKey(key)) {
        return handler.getString(key);
      }
    }
    return new NullStringProvider().getString(key);
  }

  @Override
  public boolean supportsKey(String key) {
    for (Resources handler : handlers) {
      if (handler.supportsKey(key)) {
        return true;
      }
    }
    return false;
  }

  public void add(Resources handler) {
    handlers.add(handler);
  }
}