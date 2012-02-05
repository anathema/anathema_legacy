package net.sf.anathema.platform.environment;

import net.sf.anathema.framework.environment.AnathemaEnvironment;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.util.Locale;

public class AnathemaEnvironmentTest {

  private DummyAnathemaPreferences preferences = new DummyAnathemaPreferences();

  @Test
  public void testSetDefaultLocale() throws Exception {
    preferences.setLocale(Locale.GERMAN);
    AnathemaEnvironment.initLocale(preferences);
    Assert.assertEquals(Locale.GERMAN, Locale.getDefault());
  }

  @Test
  public void testTooltipTime() throws Exception {
    int preferedTimeInSeconds = 5;
    preferences.setToolTipTime(preferedTimeInSeconds);
    AnathemaEnvironment.initTooltipManager(preferences);
    Assert.assertEquals(0, ToolTipManager.sharedInstance().getInitialDelay());
    Assert.assertEquals(0, ToolTipManager.sharedInstance().getReshowDelay());
    int expectedTimeInMiliseconds = 5000;
    Assert.assertEquals(expectedTimeInMiliseconds, ToolTipManager.sharedInstance().getDismissDelay());
  }

  @Test
  public void initializesToSystemLookAndFeelIfNoneIsSet() throws Exception {
    preferences.setUserLookAndFeel(null);
    AnathemaEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(UIManager.getSystemLookAndFeelClassName(), UIManager.getLookAndFeel().getClass().getName());
  }

  @Test
  public void initializesToSetLookAndFeel() throws Exception {
    preferences.setUserLookAndFeel(MetalLookAndFeel.class.getName());
    AnathemaEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(MetalLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }
}