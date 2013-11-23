package net.sf.anathema.hero.initialization;

import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import net.sf.anathema.hero.model.HeroModelFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ModelFactoryAutoCollectorTest {
  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void collectsHeroModels() {
    HeroModelFactory expectation = mock(HeroModelFactory.class);
    factory.add(HeroModelFactory.class, expectation);
    Collection<HeroModelFactory> result = new ModelFactoryAutoCollector(factory).collect();
    assertThat(result, CoreMatchers.hasItem(expectation));
  }
}
