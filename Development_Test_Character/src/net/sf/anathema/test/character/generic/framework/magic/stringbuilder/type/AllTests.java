package net.sf.anathema.test.character.generic.framework.magic.stringbuilder.type;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.anathema.character.generic.framework.magic.stringbuilder.type.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(ShortCharmTypeStringBuilderTest.class);
    suite.addTestSuite(VerboseCharmTypeStringBuilderTest.class);
    // $JUnit-END$
    return suite;
  }

}
