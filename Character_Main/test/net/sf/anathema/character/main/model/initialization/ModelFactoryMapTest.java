package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.main.hero.initialization.DummyFactoryCollector;
import net.sf.anathema.character.main.hero.initialization.ModelFactoryCollector;
import net.sf.anathema.character.main.hero.initialization.ModelFactoryMap;
import org.junit.Test;

public class ModelFactoryMapTest {
  private final ModelFactoryCollector collector = new DummyFactoryCollector();
  private final ModelFactoryMap map = new ModelFactoryMap(collector);

  @Test(expected = IllegalStateException.class)
  public void throwsExceptionWhenUnknownModelIsAssertedAsRequired() throws Exception {
    map.assertContainsRequiredModel("B");
  }

  @Test(expected = IllegalStateException.class)
  public void throwsExceptionWhenUnknownModelIsAssertedAsConfigured() throws Exception {
    map.assertContainsConfiguredModel("B");
  }
}