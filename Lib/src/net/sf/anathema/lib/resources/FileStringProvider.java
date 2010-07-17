package net.sf.anathema.lib.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

import net.disy.commons.core.io.IOUtilities;

public class FileStringProvider implements IStringResourceHandler {

  private final Properties properties = new Properties();

  public FileStringProvider(String fileBase, Locale locale) throws IOException {
    File propertyFile = getPropertyFile(fileBase, locale);
    if (propertyFile.exists()) {
      FileInputStream stream = new FileInputStream(propertyFile);
      try {
        properties.load(stream);
      }
      finally {
        IOUtilities.close(stream);
      }
    }
  }

  private File getPropertyFile(String fileBase, Locale locale) {
    String localeSuffix = locale.toString();
    String preferredBundleName = fileBase;
    if (localeSuffix.length() > 0) {
      preferredBundleName += "_" + localeSuffix; //$NON-NLS-1$
    }
    preferredBundleName += ".properties"; //$NON-NLS-1$
    File preferredFile = new File(preferredBundleName);
    if (preferredFile.exists()) {
      return preferredFile;
    }
    return new File(fileBase + ".properties"); //$NON-NLS-1$
  }

  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return properties.getProperty(key);
    }
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  public boolean supportsKey(String key) {
    return properties.containsKey(key);
  }
}