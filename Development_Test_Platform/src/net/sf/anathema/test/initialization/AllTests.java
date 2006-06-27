package net.sf.anathema.test.initialization;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.initialization.repository.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(LeadingPropertiesResolverTest.class);
    suite.addTestSuite(RepositoryFolderCreatorTest.class);
    suite.addTestSuite(RepositoryLocationResolverTest.class);
    // $JUnit-END$
    return suite;
  }
}