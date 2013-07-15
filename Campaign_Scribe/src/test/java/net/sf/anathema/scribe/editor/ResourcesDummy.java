package net.sf.anathema.scribe.editor;

import net.sf.anathema.lib.resources.Resources;

import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;

public class ResourcesDummy implements Resources {

  public final Map<String, String> patternsByKey = new HashMap<>();

  @Override
  public boolean supportsKey(String key) {
    return patternsByKey.containsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    if (!supportsKey(key)) {
      return "#" + key + "#";
    }
    return format(patternsByKey.get(key), arguments);
  }
}
