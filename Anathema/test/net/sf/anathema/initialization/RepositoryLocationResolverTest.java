package net.sf.anathema.initialization;

import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.platform.environment.DummyAnathemaPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryLocationResolverTest {
  private static final String SYSTEM_PROPERTY_REPOSITORY = "repository"; //$NON-NLS-1$
  private static final String SYSTEM_PROPERTY_DEFAULT_REPOSITORY = "defaultrepository"; //$NON-NLS-1$

  private DummyAnathemaPreferences anathemaPreferences;

  private void assertRepositoryLocation(String expected) {
    Assert.assertEquals(expected, new RepositoryLocationResolver(anathemaPreferences).resolve());
  }

  private String getUserHomeSystemProperty() {
    return System.getProperty("user.home"); //$NON-NLS-1$
  }

  @Before
  public void setUp() throws Exception {
    anathemaPreferences = new DummyAnathemaPreferences();
  }

  @Test
  public void testPreferenceSet() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("value"); //$NON-NLS-1$
    assertRepositoryLocation("value"); //$NON-NLS-1$
  }

  @Test
  public void testSystemProperty() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "parameter"); //$NON-NLS-1$
    assertRepositoryLocation("parameter"); //$NON-NLS-1$
  }

  @Test
  public void defaultRepositoryProperty() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_DEFAULT_REPOSITORY, "parametum"); //$NON-NLS-1$
    assertRepositoryLocation("parametum"); //$NON-NLS-1$
  }

  @Test
  public void testPreferenceWithUserHomeWildcard() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("%USER_HOME%"); //$NON-NLS-1$
    assertRepositoryLocation(getUserHomeSystemProperty());
  }

  @Test
  public void testSystemPropertyWithUserHomeWildcard() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "%USER_HOME%" + "/Liebäs"); //$NON-NLS-1$ //$NON-NLS-2$
    assertRepositoryLocation(getUserHomeSystemProperty() + "/Liebäs"); //$NON-NLS-1$ 
  }

  @After
  public void tearDown() throws Exception {
    System.clearProperty(SYSTEM_PROPERTY_REPOSITORY);
    System.clearProperty(SYSTEM_PROPERTY_DEFAULT_REPOSITORY);
  }
}