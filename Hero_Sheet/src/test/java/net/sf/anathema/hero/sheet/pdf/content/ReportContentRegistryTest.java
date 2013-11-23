package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportContentRegistryTest {
  private ConfigurableDummyObjectFactory objectFactory = new ConfigurableDummyObjectFactory();

  @Test
  public void findsFactoryForContent() throws Exception {
    ReportContentFactory factoryRegisteredForDummyContent = new DummyContentFactory();
    objectFactory.add(ReportContentFactory.class, factoryRegisteredForDummyContent);
    ReportContentFactory<DummyContent> result = new ReportContentRegistry(objectFactory, null).getFactory(DummyContent.class);
    assertThat(result, is(factoryRegisteredForDummyContent));
  }
}
