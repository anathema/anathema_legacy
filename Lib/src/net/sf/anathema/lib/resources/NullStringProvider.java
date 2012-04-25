package net.sf.anathema.lib.resources;

import net.sf.anathema.lib.resources.IStringResourceHandler;

public class NullStringProvider implements IStringResourceHandler {
  @Override
  public boolean supportsKey(String key) {
    return false;
  }

  @Override
  public String getString(String key, Object... arguments) {
    return "##" + key + "##";
  }
}
