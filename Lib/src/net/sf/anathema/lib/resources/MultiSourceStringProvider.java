package net.sf.anathema.lib.resources;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class MultiSourceStringProvider implements IStringResourceHandler {
  private final List<IStringResourceHandler> handlers = new ArrayList<>();

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return getString(key);
    }
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  private String getString(String key) {
    for (IStringResourceHandler handler : handlers) {
      if (handler.supportsKey(key)) {
        return handler.getString(key);
      }
    }
    return new NullStringProvider().getString(key);
  }

  @Override
  public boolean supportsKey(String key) {
    for (IStringResourceHandler handler : handlers) {
      if (handler.supportsKey(key)) {
        return true;
      }
    }
    return false;
  }

  public void add(IStringResourceHandler handler) {
    handlers.add(handler);
  }
}