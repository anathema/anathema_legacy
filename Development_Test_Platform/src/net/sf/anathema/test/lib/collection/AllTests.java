package net.sf.anathema.test.lib.collection;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib.collection.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(ListOrderedSetTest.class);
    // $JUnit-END$
    return suite;
  }

}
