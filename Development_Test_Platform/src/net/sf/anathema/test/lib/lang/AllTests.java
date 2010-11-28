package net.sf.anathema.test.lib.lang;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    net.sf.anathema.test.lib.lang.clone.AllTests.class,
    IntegerUtilitiesTest.class,
    ArrayUtiltiesTest.class,
    StringUtilitiesTest.class })
public class AllTests {
  // nothing to do
}