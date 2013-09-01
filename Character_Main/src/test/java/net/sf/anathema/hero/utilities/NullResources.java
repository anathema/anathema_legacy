package net.sf.anathema.hero.utilities;

import net.sf.anathema.framework.environment.Resources;

public class NullResources implements Resources {

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return key;
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(key);
    stringBuilder.append(",");
    for (Object arg : arguments) {
      stringBuilder.append(String.valueOf(arg));
      stringBuilder.append(",");
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean supportsKey(String key) {
    return true;
  }
}