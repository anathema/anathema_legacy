package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.environment.dependencies.AnnotatedDummy;
import net.sf.anathema.framework.environment.dependencies.DummyInterface;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplicationEnvironmentTest {
  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();
  private ApplicationEnvironment environment = new ApplicationEnvironment(null, null, null, factory, null);

  @Test
  public void forwardsImplementorInstantiationsToObjectFactory() {
    DummyInterface expectation = new AnnotatedDummy();
    factory.add(DummyInterface.class, expectation);
    Collection<DummyInterface> actualResult = environment.instantiateAllImplementers(DummyInterface.class);
    assertThat(actualResult, hasItem(expectation));
  }

  @Test
  public void forwardsSingleImplementorInstantiationsToObjectFactory() {
    DummyInterface expectation = new AnnotatedDummy();
    factory.add(DummyInterface.class, expectation);
    DummyInterface actualResult = environment.instantiateOnlyImplementer(DummyInterface.class);
    assertThat(actualResult, is(expectation));
  }
}
