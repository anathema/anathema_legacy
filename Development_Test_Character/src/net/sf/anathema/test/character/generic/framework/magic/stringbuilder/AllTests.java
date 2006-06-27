package net.sf.anathema.test.character.generic.framework.magic.stringbuilder;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.type.VerboseCharmTypeStringBuilderTest;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.magic.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(EssenceCostStringBuilderTest.class);
    suite.addTestSuite(WillpowerCostStringBuilderTest.class);
    suite.addTestSuite(VerboseCharmTypeStringBuilderTest.class);
    suite.addTestSuite(HealthCostStringBuilderTest.class);
    suite.addTestSuite(MagicInfoStringConcatenatorTest.class);
    //$JUnit-END$
    return suite;
  }

}
