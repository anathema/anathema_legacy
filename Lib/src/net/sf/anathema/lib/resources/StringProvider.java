package net.sf.anathema.lib.resources;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StringProvider implements IStringResourceHandler {
  private ResourceBundle resourceBundle;
  private final List<String> keyList = new ArrayList<>();

  public StringProvider(String bundleName, Locale locale) {
    setLocale(locale, bundleName);
  }

  private void setLocale(Locale locale, String resourceBundleName) {
    if (locale == null) {
      throw new IllegalArgumentException("locale is null."); //$NON-NLS-1$
    }
    resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale);
    for (Enumeration<String> keys = resourceBundle.getKeys(); keys.hasMoreElements();) {
      this.keyList.add(keys.nextElement());
    }
  }

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return resourceBundle.getString(key);
    }
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  @Override
  public boolean supportsKey(String key) {
    return keyList.contains(key);
  }
}