package net.sf.anathema.test.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    AllTests.class,
    net.sf.anathema.test.lib.lang.AllTests.class,
    net.sf.anathema.test.lib.gui.list.veto.AllTests.class,
    net.sf.anathema.test.lib.collection.AllTests.class })
public class AllLibTests {
  // nothing to do
}