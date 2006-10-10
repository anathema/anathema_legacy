package net.sf.anathema.test.platform.configuration;

import java.util.prefs.Preferences;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.junit.Test;

public class AnathemaPreferencesTest extends BasicTestCase {

  private Preferences preferences;
  private IAnathemaPreferences anathemaPreferences;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    preferences = Preferences.userRoot().node("testRoot"); //$NON-NLS-1$
    anathemaPreferences = new AnathemaPreferences(preferences);
  }

  public void testDefaultRepository() throws Exception {
    assertEquals("./repository/", anathemaPreferences.getRepositoryLocationPreference(null)); //$NON-NLS-1$
  }

  public void testCustomizedRepository() throws Exception {
    preferences.put("Repository", "C:/exalted/ist/toll/"); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals("C:/exalted/ist/toll/", anathemaPreferences.getRepositoryLocationPreference(null)); //$NON-NLS-1$
  }

  @Test
  public void customizedDefaultRepository() throws Exception {
    assertEquals("C:/exalted/ist/toll/", anathemaPreferences.getRepositoryLocationPreference("C:/exalted/ist/toll/")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testMaximizedDefault() throws Exception {
    assertFalse(anathemaPreferences.initMaximized());
  }

  public void testInitMaximized() throws Exception {
    preferences.putBoolean("Maximize", true); //$NON-NLS-1$
    assertTrue(anathemaPreferences.initMaximized());
  }

  public void testLookAndFeelDefault() throws Exception {
    assertFalse(anathemaPreferences.isMetalLookAndFeelForced());
  }

  public void testForceMetalLookAndFeel() throws Exception {
    preferences.putBoolean("ForceMetalLookAndFeel", true); //$NON-NLS-1$
    assertTrue(anathemaPreferences.isMetalLookAndFeelForced());
  }

  public void testDefaultToolTipTime() throws Exception {
    assertEquals(10, anathemaPreferences.getTooltipTimePreference());
  }

  public void testChangedToolTipTime() throws Exception {
    preferences.putInt("ToolTipTime", 14); //$NON-NLS-1$
    assertEquals(14, anathemaPreferences.getTooltipTimePreference());
  }

  public void testDefaultLocale() throws Exception {
    assertEquals("en", anathemaPreferences.getPreferredLocale().getLanguage()); //$NON-NLS-1$
  }

  public void testSpanishLocale() throws Exception {
    preferences.put("Locale", "Spanish"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("es", anathemaPreferences.getPreferredLocale().getLanguage()); //$NON-NLS-1$
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    preferences.removeNode();
  }
}