package net.sf.anathema.test.platform.environment;

import java.util.Locale;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.lib.testing.BasicTestCase;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class AnathemaEnvironmentTest extends BasicTestCase {

  private DummyAnathemaPreferences preferences;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    System.setProperty("development", "true"); //$NON-NLS-1$//$NON-NLS-2$
    preferences = new DummyAnathemaPreferences();
  }

  public void testNotDevelopment() throws Exception {
    System.clearProperty("development"); //$NON-NLS-1$
    assertFalse(AnathemaEnvironment.isDevelopment());
  }

  public void testDevelopment() throws Exception {
    assertTrue(AnathemaEnvironment.isDevelopment());
  }

  public void testSetDefaultLocale() throws Exception {
    preferences.setLocale(Locale.GERMAN);
    AnathemaEnvironment.initLocale(preferences);
    assertEquals(Locale.GERMAN, Locale.getDefault());
  }

  public void testTooltipTime() throws Exception {
    int preferedTimeInSeconds = 5;
    preferences.setToolTipTime(preferedTimeInSeconds);
    AnathemaEnvironment.initTooltipManager(preferences);
    assertEquals(0, ToolTipManager.sharedInstance().getInitialDelay());
    assertEquals(0, ToolTipManager.sharedInstance().getReshowDelay());
    int expectedTimeInMiliseconds = 5000;
    assertEquals(expectedTimeInMiliseconds, ToolTipManager.sharedInstance().getDismissDelay());
  }

  public void testWindowsLookAndFeel() throws Exception {
    preferences.setForceMetalLookAndFeel(false);
    AnathemaEnvironment.initLookAndFeel(preferences);
    assertEquals(WindowsLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }

  public void testForceMetalLookAndFeel() throws Exception {
    preferences.setForceMetalLookAndFeel(true);
    AnathemaEnvironment.initLookAndFeel(preferences);
    assertEquals(MetalLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    System.clearProperty("development"); //$NON-NLS-1$    
  }

}
