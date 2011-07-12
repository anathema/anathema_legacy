package net.sf.anathema.lib.resources;

import java.text.MessageFormat;

public class DefaultStringProvider implements IStringResourceHandler {
  
  private final String defaultKey;

  public DefaultStringProvider(String defaultKey) {
    this.defaultKey = defaultKey;
  }

  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      String[] splitKey = key.split("\\."); //$NON-NLS-1$
      if (splitKey[splitKey.length - 1].equals(defaultKey)) {
        return splitKey[splitKey.length - 2];
      }
    }
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  public boolean supportsKey(String key) {
    return key.endsWith("." + defaultKey); //$NON-NLS-1$
  }
}