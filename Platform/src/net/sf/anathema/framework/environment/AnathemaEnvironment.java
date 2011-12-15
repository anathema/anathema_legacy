package net.sf.anathema.framework.environment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.logging.LogManager;

import javax.swing.ToolTipManager;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;

public class AnathemaEnvironment {
  private static final String SYSTEM_PROPERTY_DEVELOPMENT = "development"; //$NON-NLS-1$

  public static void initLogging() {
    System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "error"); //$NON-NLS-1$ //$NON-NLS-2$
    String propertyString = "level=ERROR"; //$NON-NLS-1$
    InputStream logPropertyInputStream = new ByteArrayInputStream(propertyString.getBytes());
    try {
      LogManager.getLogManager().readConfiguration(logPropertyInputStream);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void initTooltipManager(IAnathemaPreferences anathemaPreferences) {
    ToolTipManager.sharedInstance().setInitialDelay(0);
    ToolTipManager.sharedInstance().setReshowDelay(0);
    int toolTipTime = anathemaPreferences.getTooltipTimePreference();
    ToolTipManager.sharedInstance().setDismissDelay(toolTipTime * 1000);
  }

  public static void initLookAndFeel(IAnathemaPreferences anathemaPreferences) {
    new LookAndFeelInitializer(anathemaPreferences).initialize();
  }

  public static void initLocale(IAnathemaPreferences preferences) {
    Locale.setDefault(preferences.getPreferredLocale());
  }

  public static final boolean isDevelopment() {
    return System.getProperty(SYSTEM_PROPERTY_DEVELOPMENT) != null;
  }
}