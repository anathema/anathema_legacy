package net.sf.anathema.test.initialization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    LeadingPropertiesResolverTest.class,
    RepositoryFolderCreatorTest.class,
    RepositoryLocationResolverTest.class })
public class AllTests {
  // nothing to do
}