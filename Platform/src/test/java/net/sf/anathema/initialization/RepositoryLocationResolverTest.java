package net.sf.anathema.initialization;

import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.platform.environment.DummyInitializationPreferences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryLocationResolverTest {

  private DummyInitializationPreferences anathemaPreferences;

  private void assertRepositoryLocation(String expected) {
    Assert.assertEquals(expected, new RepositoryLocationResolver(anathemaPreferences).resolve());
  }

  private String getUserHomeSystemProperty() {
    return System.getProperty("user.home");
  }

  @Before
  public void setUp() throws Exception {
    anathemaPreferences = new DummyInitializationPreferences();
  }

  @Test
  public void testPreferenceSet() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("value");
    assertRepositoryLocation("value");
  }

  @Test
  public void testPreferenceWithUserHomeWildcard() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("%USER_HOME%");
    assertRepositoryLocation(getUserHomeSystemProperty());
  }
}