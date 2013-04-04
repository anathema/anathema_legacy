package net.sf.anathema.lib.dummy;

import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.resources.Resources;

import java.util.HashMap;
import java.util.Map;

public class DummyResources implements Resources {
  private final Map<String, String> stringMap = new HashMap<>();

  @Override
  public boolean supportsKey(String key) {
    return stringMap.containsKey(key);
  }

  public void putString(String key, String value) {
    stringMap.put(key, value);
  }

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return stringMap.get(key);
    }
    throw new NotYetImplementedException();
  }
}