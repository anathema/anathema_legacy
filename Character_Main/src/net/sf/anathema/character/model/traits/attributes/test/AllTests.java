package net.sf.anathema.character.model.traits.attributes.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for " + AllTests.class.getPackage().getName()); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(AttributeTypeTest.class);
    suite.addTestSuite(AttributeGroupTypeTest.class);
    //$JUnit-END$
    return suite;
  }
}
