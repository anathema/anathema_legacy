package net.sf.anathema.platform.configuration;

import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.metal.MetalLookAndFeel;
import java.util.prefs.Preferences;

public class AnathemaPreferencesTest {

  private Preferences preferences;
  private IInitializationPreferences initializationPreferences;

  @Before
  public void setUp() throws Exception {
    preferences = Preferences.userRoot().node("testRoot");
    initializationPreferences = new InitializationPreferences(preferences);
  }

  @Test
  public void testDefaultRepository() throws Exception {
    Assert.assertEquals("./repository/", initializationPreferences.getRepositoryLocationPreference(null));
  }

  @Test
  public void testCustomizedRepository() throws Exception {
    preferences.put("RepositoryForAnathema4orHigher", "C:/exalted/ist/toll/");
    Assert.assertEquals("C:/exalted/ist/toll/", initializationPreferences.getRepositoryLocationPreference(null));
  }

  @Test
  public void customizedDefaultRepository() throws Exception {
    Assert.assertEquals(
      "C:/exalted/ist/toll/", initializationPreferences.getRepositoryLocationPreference("C:/exalted/ist/toll/"));
  }

  @Test
  public void testMaximizedDefault() throws Exception {
    Assert.assertFalse(initializationPreferences.initMaximized());
  }

  @Test
  public void testInitMaximized() throws Exception {
    preferences.putBoolean("Maximize", true);
    Assert.assertTrue(initializationPreferences.initMaximized());
  }

  @Test
  public void testLookAndFeelDefault() throws Exception {
    Assert.assertNull(initializationPreferences.getUserLookAndFeel());
  }

  @Test
  public void testUserLookAndFeel() throws Exception {
    String metalName = MetalLookAndFeel.class.getName();
    preferences.put("UserLookAndFeel", metalName);
    Assert.assertEquals(metalName, initializationPreferences.getUserLookAndFeel());
  }

  @Test
  public void testDefaultToolTipTime() throws Exception {
    Assert.assertEquals(10, initializationPreferences.getTooltipTimePreference());
  }

  @Test
  public void testChangedToolTipTime() throws Exception {
    preferences.putInt("ToolTipTime", 14);
    Assert.assertEquals(14, initializationPreferences.getTooltipTimePreference());
  }

  @Test
  public void testDefaultLocale() throws Exception {
    Assert.assertEquals("en", initializationPreferences.getPreferredLocale().getLanguage());
  }

  @Test
  public void testSpanishLocale() throws Exception {
    preferences.put("Locale", "Spanish");
    Assert.assertEquals("es", initializationPreferences.getPreferredLocale().getLanguage());
  }

  @After
  public void tearDown() throws Exception {
    preferences.removeNode();
  }
}