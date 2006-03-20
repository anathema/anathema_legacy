package net.sf.anathema.character.impl.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterImplTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.core"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.impl.model.advance.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.creation.bonus.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.creation.bonus.basic.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.charm.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.charm.combo.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.model.traits.attributes.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.traits.creation.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.impl.model.traits.test.AllTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}