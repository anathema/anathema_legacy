package net.sf.anathema.character;

import net.sf.anathema.lib.resources.Resources;

public class NullResources implements Resources {

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return key;
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(key);
    stringBuilder.append(","); //$NON-NLS-1$
    for (Object arg : arguments) {
      stringBuilder.append(String.valueOf(arg));
      stringBuilder.append(","); //$NON-NLS-1$
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean supportsKey(String key) {
    return true;
  }
}