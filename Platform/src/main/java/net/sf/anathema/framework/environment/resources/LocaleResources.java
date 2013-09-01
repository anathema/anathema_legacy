package net.sf.anathema.framework.environment.resources;

import net.sf.anathema.framework.environment.Resources;

import java.util.Locale;

public class LocaleResources implements Resources {

  private final MultiSourceStringProvider stringHandler = new MultiSourceStringProvider();
  private final StringProviderFactory factory = new StringProviderFactory(getLocale());

  public LocaleResources() {
    stringHandler.add(new DefaultStringProvider("Literal"));
  }

  public void addResourceBundle(ResourceFile resource) {
    stringHandler.add(factory.create(resource));
  }

  @Override
  public boolean supportsKey(String key) {
    return stringHandler.supportsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    return stringHandler.getString(key, arguments);
  }

  private Locale getLocale() {
    return Locale.getDefault();
  }
}