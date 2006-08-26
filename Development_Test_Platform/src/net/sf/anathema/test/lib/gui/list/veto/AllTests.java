//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.test.lib.gui.list.veto;

import junit.framework.Test;
import junit.framework.TestSuite;

// NOT_PUBLISHED
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for de.disy.lib.gui.list.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(VetoableListSelectionModelTest.class);
    //$JUnit-END$
    return suite;
  }
}
