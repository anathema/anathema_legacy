package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharmDisplayPropertiesMapTest {

  @Test
  public void findsPropertiesForCharacterType() throws Exception {
    TreePresentationProperties expectation = new DummyCharmPresentationProperties();
    ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();
    factory.add(CharmPresentationProperties.class, expectation);
    DummyExaltCharacterType characterType = new DummyExaltCharacterType();
    TreePresentationProperties properties = new CharmDisplayPropertiesMap(factory).getDisplayProperties(characterType);
    assertThat(properties, is(expectation));


  }
}
