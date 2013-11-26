package net.sf.anathema.initialization;

import com.google.common.collect.Iterables;
import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnathemaExtensionCollectionTest {

  public static final String DUMMYID = "DUMMYID";

  @Test
  public void containsExtensionsWithId() throws Exception {
    DummyExtension extension = new DummyExtension();
    ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();
    factory.add(IAnathemaExtension.class, extension);
    AnathemaExtensionCollection extensionCollection = new AnathemaExtensionCollection(factory);
    ExtensionWithId element = Iterables.getOnlyElement(extensionCollection);
    assertThat(element, is(new ExtensionWithId("DUMMYID", extension)));
  }
}
