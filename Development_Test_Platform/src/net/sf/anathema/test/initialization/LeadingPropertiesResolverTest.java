package net.sf.anathema.test.initialization;

import java.util.Properties;

import net.sf.anathema.initialization.repository.LeadingPropertyResolver;
import net.sf.anathema.lib.testing.BasicTestCase;

public class LeadingPropertiesResolverTest extends BasicTestCase {

  private static final String PROPERTY_NAME = "propertyName"; //$NON-NLS-1$
  private static final String WILDCARD = "%WILDCARD%"; //$NON-NLS-1$
  private static final String PROPERTY_VALUE = "propertyValue"; //$NON-NLS-1$
  private Properties properties;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.properties = new Properties();
    properties.put(PROPERTY_NAME, PROPERTY_VALUE);
  }

  public void testStandardString() throws Exception {
    assertParsesTo("NoSpecials", "NoSpecials"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testUserHomeString() throws Exception {
    assertParsesTo(PROPERTY_VALUE, WILDCARD);
  }

  public void testExtendedUserHomeString() throws Exception {
    assertParsesTo(PROPERTY_VALUE + "/und drinnen", WILDCARD + "/und drinnen"); //$NON-NLS-1$//$NON-NLS-2$
  }

  private void assertParsesTo(String expected, String path) {
    LeadingPropertyResolver resolver = new LeadingPropertyResolver(properties, WILDCARD, PROPERTY_NAME);
    assertEquals(expected, resolver.parse(path));
  }
}