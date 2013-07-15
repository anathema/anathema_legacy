package net.sf.anathema.initialization;

import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.platform.environment.DummyInitializationPreferences;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryLocationResolverTest {
  private static final String SYSTEM_PROPERTY_REPOSITORY = "repository";
  private static final String SYSTEM_PROPERTY_DEFAULT_REPOSITORY = "defaultrepository";

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
  public void testSystemProperty() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "parameter");
    assertRepositoryLocation("parameter");
  }

  @Test
  public void defaultRepositoryProperty() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_DEFAULT_REPOSITORY, "parametum");
    assertRepositoryLocation("parametum");
  }

  @Test
  public void testPreferenceWithUserHomeWildcard() throws Exception {
    anathemaPreferences.setRepositoryLocationPreference("%USER_HOME%");
    assertRepositoryLocation(getUserHomeSystemProperty());
  }

  @Test
  public void testSystemPropertyWithUserHomeWildcard() throws Exception {
    System.setProperty(SYSTEM_PROPERTY_REPOSITORY, "%USER_HOME%" + "/Liebaes");
    assertRepositoryLocation(getUserHomeSystemProperty() + "/Liebaes");
  }

  @After
  public void tearDown() throws Exception {
    System.clearProperty(SYSTEM_PROPERTY_REPOSITORY);
    System.clearProperty(SYSTEM_PROPERTY_DEFAULT_REPOSITORY);
  }
}