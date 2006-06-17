package net.sf.anathema.test.character;

import net.sf.anathema.demo.character.AllCharacterDemos;
import junit.framework.Test;
import junit.framework.TestSuite;
import de.jdemo.junit.Demo2TestConverter;

public class AllCharacterTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.demo.character.equipment.item.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.abyssal.additional.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.equipment.impl.creation.model.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.framework.charm.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.magic.charms.duration.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.persistence.magic.load.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.generic.traits.types.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.library.trait.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.main.impl.charm.AllTests.suite());
    suite.addTest(net.sf.anathema.test.character.main.impl.combo.AllTests.suite());
    suite.addTest(Demo2TestConverter.createTest(AllCharacterDemos.suite()));
    //$JUnit-BEGIN$
    suite.addTestSuite(CharacterUtilitiesTest.class);
    //$JUnit-END$
    return suite;
  }
}