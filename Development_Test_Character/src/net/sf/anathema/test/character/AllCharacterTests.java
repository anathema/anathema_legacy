package net.sf.anathema.test.character;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.jdemo.junit.Demo2TestConverter;

public class AllCharacterTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.character.equipment.impl.creation.model.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.equipment.item.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.impl.magic.persistence.builder.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.magic.charms.duration.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.traits.types.AllTests.suite());
    suite.addTest(Demo2TestConverter.createTest(AllCharacterDemos.suite()));
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}