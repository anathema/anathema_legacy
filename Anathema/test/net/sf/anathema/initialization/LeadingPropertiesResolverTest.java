package net.sf.anathema.initialization;

import java.util.Properties;

import net.sf.anathema.initialization.repository.LeadingPropertyResolver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeadingPropertiesResolverTest {

  private static final String PROPERTY_NAME = "propertyName"; //$NON-NLS-1$
  private static final String WILDCARD = "%WILDCARD%"; //$NON-NLS-1$
  private static final String PROPERTY_VALUE = "propertyValue"; //$NON-NLS-1$
  private Properties properties;

  @Before
  public void setUp() throws Exception {
    this.properties = new Properties();
    properties.put(PROPERTY_NAME, PROPERTY_VALUE);
  }

  @Test
  public void testStandardString() throws Exception {
    assertParsesTo("NoSpecials", "NoSpecials"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testUserHomeString() throws Exception {
    assertParsesTo(PROPERTY_VALUE, WILDCARD);
  }

  @Test
  public void testExtendedUserHomeString() throws Exception {
    assertParsesTo(PROPERTY_VALUE + "/und drinnen", WILDCARD + "/und drinnen"); //$NON-NLS-1$//$NON-NLS-2$
  }

  private void assertParsesTo(String expected, String path) {
    LeadingPropertyResolver resolver = new LeadingPropertyResolver(properties, WILDCARD, PROPERTY_NAME);
    Assert.assertEquals(expected, resolver.parse(path));
  }
}