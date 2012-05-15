package net.sf.anathema.lib.gui.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LibGuiMessages {
  private static final String BUNDLE_NAME = "net.sf.anathema.lib.gui.messages.messages";//$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  private LibGuiMessages() {
    //no instance available
  }

  public static String getString(final String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    }
    catch (final MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}