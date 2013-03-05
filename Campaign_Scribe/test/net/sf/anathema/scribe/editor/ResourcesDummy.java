package net.sf.anathema.scribe.editor;

import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;

public class ResourcesDummy implements IResources {

  public final Map<String, String> patternsByKey = new HashMap<>();

  @Override
  public Image getImage(Class<?> inquirer, String relativePath) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Icon getImageIcon(Class<?> inquirer, String relativePath) {
    throw new UnsupportedOperationException();
  }

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
