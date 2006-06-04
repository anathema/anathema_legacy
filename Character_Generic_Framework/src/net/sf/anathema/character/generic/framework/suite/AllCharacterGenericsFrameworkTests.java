package net.sf.anathema.character.generic.framework.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterGenericsFrameworkTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.generic.framework.magic.treelayout.layering.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.treelayout.nodes.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.treelayout.ordering.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.treelayout.util.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.stringbuilder.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.stringbuilder.type.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.magic.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.creation.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.rules.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.essence.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.experience.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.magic.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.presentation.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.trait.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.trait.pool.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.util.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.trait.alternate.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.framework.xml.health.test.AllTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}