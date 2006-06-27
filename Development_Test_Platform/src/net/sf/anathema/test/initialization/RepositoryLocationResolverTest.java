package net.sf.anathema.test.initialization;

import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.platform.environment.DummyAnathemaPreferences;

public class RepositoryLocationResolverTest extends BasicTestCase {
  private static final String SYSTEM_PROPERTY_REPOSITORY = "repository"; //$NON-NLS-1$
  private DummyAnathemaPreferences anathemaPreferences;

  private void assertRepositoryLocation(String expected) {
    assertEquals(expected, new RepositoryLocationResolver(anathemaPreferences).resolve());
  }

  private String getUserHomeSystemProperty() {
    return System.getProperty("user.home"); //$NON-NLS-1$
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    anathemaPreferences = new DummyAnathemaPreferences();
  }

  public void testPreferenceSet() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("value"); //$NON-NLS-1$
    assertRepositoryLocation("value"); //$NON-NLS-1$
  }

  public void testSystemProperty() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "parameter"); //$NON-NLS-1$
    assertRepositoryLocation("parameter"); //$NON-NLS-1$
  }

  public void testPreferenceWithUserHomeWildcard() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("%USER_HOME%"); //$NON-NLS-1$
    assertRepositoryLocation(getUserHomeSystemProperty());
  }

  public void testSystemPropertyWithUserHomeWildcard() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "%USER_HOME%" + "/Liebäs"); //$NON-NLS-1$ //$NON-NLS-2$
    assertRepositoryLocation(getUserHomeSystemProperty() + "/Liebäs"); //$NON-NLS-1$ 
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    System.clearProperty(SYSTEM_PROPERTY_REPOSITORY);
  }
}