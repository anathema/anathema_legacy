package net.sf.anathema.character.generic.impl.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterGenericImplTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.generic.impl.magic.persistence.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.impl.magic.persistence.writer.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.impl.template.points.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.impl.magic.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.impl.template.test.AllTests.suite());
    // $JUnit-BEGIN$
    // $JUnit-END$
    return suite;
  }
}