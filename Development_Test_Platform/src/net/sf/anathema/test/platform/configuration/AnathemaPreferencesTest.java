package net.sf.anathema.test.platform.configuration;

import java.util.prefs.Preferences;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnathemaPreferencesTest {

  private Preferences preferences;
  private IAnathemaPreferences anathemaPreferences;

  @Before
  public void setUp() throws Exception {
    preferences = Preferences.userRoot().node("testRoot"); //$NON-NLS-1$
    anathemaPreferences = new AnathemaPreferences(preferences);
  }

  public void testDefaultRepository() throws Exception {
    Assert.assertEquals("./repository/", anathemaPreferences.getRepositoryLocationPreference(null)); //$NON-NLS-1$
  }

  public void testCustomizedRepository() throws Exception {
    preferences.put("Repository", "C:/exalted/ist/toll/"); //$NON-NLS-1$//$NON-NLS-2$
    Assert.assertEquals("C:/exalted/ist/toll/", anathemaPreferences.getRepositoryLocationPreference(null)); //$NON-NLS-1$
  }

  @Test
  public void customizedDefaultRepository() throws Exception {
    Assert.assertEquals(
        "C:/exalted/ist/toll/", anathemaPreferences.getRepositoryLocationPreference("C:/exalted/ist/toll/")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testMaximizedDefault() throws Exception {
    Assert.assertFalse(anathemaPreferences.initMaximized());
  }

  @Test
  public void testInitMaximized() throws Exception {
    preferences.putBoolean("Maximize", true); //$NON-NLS-1$
    Assert.assertTrue(anathemaPreferences.initMaximized());
  }

  @Test
  public void testLookAndFeelDefault() throws Exception {
    Assert.assertFalse(anathemaPreferences.isMetalLookAndFeelForced());
  }

  @Test
  public void testForceMetalLookAndFeel() throws Exception {
    preferences.putBoolean("ForceMetalLookAndFeel", true); //$NON-NLS-1$
    Assert.assertTrue(anathemaPreferences.isMetalLookAndFeelForced());
  }

  @Test
  public void testDefaultToolTipTime() throws Exception {
    Assert.assertEquals(10, anathemaPreferences.getTooltipTimePreference());
  }

  @Test
  public void testChangedToolTipTime() throws Exception {
    preferences.putInt("ToolTipTime", 14); //$NON-NLS-1$
    Assert.assertEquals(14, anathemaPreferences.getTooltipTimePreference());
  }

  @Test
  public void testDefaultLocale() throws Exception {
    Assert.assertEquals("en", anathemaPreferences.getPreferredLocale().getLanguage()); //$NON-NLS-1$
  }

  @Test
  public void testSpanishLocale() throws Exception {
    preferences.put("Locale", "Spanish"); //$NON-NLS-1$ //$NON-NLS-2$
    Assert.assertEquals("es", anathemaPreferences.getPreferredLocale().getLanguage()); //$NON-NLS-1$
  }

  @After
  public void tearDown() throws Exception {
    preferences.removeNode();
  }
}