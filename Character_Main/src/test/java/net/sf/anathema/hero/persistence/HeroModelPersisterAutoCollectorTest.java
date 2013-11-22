package net.sf.anathema.hero.persistence;

import net.sf.anathema.framework.environment.ApplicationEnvironment;
import net.sf.anathema.framework.environment.ObjectFactory;
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

public class HeroModelPersisterAutoCollectorTest {
  private ObjectFactory factory = mock(ObjectFactory.class);
  private HeroModelPersisterAutoCollector collector = new HeroModelPersisterAutoCollector(factory);
  private ArrayList<HeroModelPersister> resultList = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    when(factory.instantiateAllImplementers(HeroModelPersister.class)).thenReturn(resultList);
  }

  @Test
  public void forwardsImplementorInstantiationsToObjectFactory() {
    HeroModelPersister expectation = mock(HeroModelPersister.class);
    resultList.add(expectation);
    Collection<HeroModelPersister> actualResult = collector.collect();
    assertThat(actualResult, hasItem(expectation));
  }
}