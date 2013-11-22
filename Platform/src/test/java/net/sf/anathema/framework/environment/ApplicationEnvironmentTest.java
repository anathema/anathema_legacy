package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.environment.dependencies.AnnotatedDummy;
import net.sf.anathema.framework.environment.dependencies.DummyInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationEnvironmentTest {
  private ObjectFactory factory = mock(ObjectFactory.class);
  private ApplicationEnvironment environment = new ApplicationEnvironment(null, null, null, factory, null);
  private ArrayList resultList = new ArrayList();

  @Before
  public void setUp() throws Exception {
    when(factory.instantiateAllImplementers(DummyInterface.class)).thenReturn(resultList);
  }

  @Test
  public void forwardsImplementorInstantiationsToObjectFactory() {
    DummyInterface expectation = new AnnotatedDummy();
    resultList.add(expectation);
    Collection<DummyInterface> actualResult = environment.instantiateAllImplementers(DummyInterface.class);
    assertThat(actualResult, hasItem(expectation));

  }
}
