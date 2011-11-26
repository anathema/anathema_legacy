package net.sf.anathema.test.platform.environment;

import java.util.Locale;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.sf.anathema.framework.environment.AnathemaEnvironment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class AnathemaEnvironmentTest {

  private DummyAnathemaPreferences preferences;

  @Before
  public void setUp() throws Exception {
    System.setProperty("development", "true"); //$NON-NLS-1$//$NON-NLS-2$
    preferences = new DummyAnathemaPreferences();
  }

  @Test
  public void testNotDevelopment() throws Exception {
    System.clearProperty("development"); //$NON-NLS-1$
    Assert.assertFalse(AnathemaEnvironment.isDevelopment());
  }

  @Test
  public void testDevelopment() throws Exception {
    Assert.assertTrue(AnathemaEnvironment.isDevelopment());
  }

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
  public void testWindowsLookAndFeel() throws Exception {
    preferences.setUserLookAndFeel(null);
    AnathemaEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(WindowsLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }

  @Test
  public void testForceMetalLookAndFeel() throws Exception {
    preferences.setUserLookAndFeel(MetalLookAndFeel.class.getName());
    AnathemaEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(MetalLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }

  @After
  public void tearDown() throws Exception {
    System.clearProperty("development"); //$NON-NLS-1$    
  }

}
