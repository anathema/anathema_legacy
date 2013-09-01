package net.sf.anathema.framework.environment.resources;

import net.sf.anathema.framework.environment.Resources;

public class NullStringProvider implements Resources {
  @Override
  public boolean supportsKey(String key) {
    return false;
  }

  @Override
  public String getString(String key, Object... arguments) {
    return "##" + key + "##";
  }
}
