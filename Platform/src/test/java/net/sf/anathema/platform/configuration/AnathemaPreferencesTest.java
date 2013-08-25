package net.sf.anathema.platform.configuration;

import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.configuration.InitializationPreferences;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.prefs.Preferences;

public class AnathemaPreferencesTest {

  private Preferences preferences;
  private RepositoryPreference initializationPreferences;

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

  @After
  public void tearDown() throws Exception {
    preferences.removeNode();
  }
}