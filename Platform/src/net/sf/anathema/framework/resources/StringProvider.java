package net.sf.anathema.framework.resources;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class StringProvider implements IStringResourceHandler {

  protected Logger logger = Logger.getLogger(getClass());
  private ResourceBundle resourceBundle;
  private final List<String> keyList = new ArrayList<String>();

  protected StringProvider(String resourceBundle) {
    this(resourceBundle, Locale.getDefault());
  }

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

  public String getString(String key) {
    return resourceBundle.getString(key);
  }

  public String getString(String key, Object[] arguments) {
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  public boolean supportsKey(String key) {
    return keyList.contains(key);
  }
}