package net.sf.anathema.initialization;

import net.sf.anathema.initialization.repository.LeadingPropertyResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class LeadingPropertiesResolverTest {

  private static final String PROPERTY_NAME = "propertyName";
  private static final String WILDCARD = "%WILDCARD%";
  private static final String PROPERTY_VALUE = "propertyValue";
  private Properties properties;

  @Before
  public void setUp() throws Exception {
    this.properties = new Properties();
    properties.put(PROPERTY_NAME, PROPERTY_VALUE);
  }

  @Test
  public void testStandardString() throws Exception {
    assertParsesTo("NoSpecials", "NoSpecials");
  }

  @Test
  public void testUserHomeString() throws Exception {
    assertParsesTo(PROPERTY_VALUE, WILDCARD);
  }

  @Test
  public void testExtendedUserHomeString() throws Exception {

    assertParsesTo(PROPERTY_VALUE + "/und drinnen", WILDCARD + "/und drinnen");
  }

  private void assertParsesTo(String expected, String path) {
    LeadingPropertyResolver resolver = new LeadingPropertyResolver(properties, WILDCARD, PROPERTY_NAME);
    Assert.assertEquals(expected, resolver.parse(path));
  }
}