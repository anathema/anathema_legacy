package net.sf.anathema.test.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    net.sf.anathema.test.platform.itemdata.AllTests.class,
    net.sf.anathema.test.platform.configuration.AllTests.class,
    net.sf.anathema.test.platform.environment.AllTests.class, })
public class AllPlatformTests {
  // nothing to do
}