package net.sf.anathema.hero.persistence;

import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class HeroModelPersisterAutoCollectorTest {
  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();
  private HeroModelPersisterAutoCollector collector = new HeroModelPersisterAutoCollector(factory);

  @Test
  public void forwardsImplementorInstantiationsToObjectFactory() {
    HeroModelPersister expectation = mock(HeroModelPersister.class);
    factory.add(HeroModelPersister.class, expectation);
    Collection<HeroModelPersister> actualResult = collector.collect();
    assertThat(actualResult, hasItem(expectation));
  }
}