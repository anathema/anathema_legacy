package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.Registry;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExtensionWithIdTest {

  IApplicationModel model = mock(IApplicationModel.class);
  Repository repository = mock(Repository.class);
  IAnathemaExtension extension = mock(IAnathemaExtension.class);
  Environment environment = mock(Environment.class);

  @Test
  public void handsAllParametersToExtension() throws Exception {
    when(model.getRepository()).thenReturn(repository);
    when(model.getExtensionPointRegistry()).thenReturn(new Registry<String, IAnathemaExtension>());
    new ExtensionWithId("id", extension).register(model, environment);
    verify(extension).initialize(repository, environment, environment);
  }

  @Test
  public void equalsExtensionsWithIdenticalIdsAndPayload() throws Exception {
    assertThat(new ExtensionWithId("id", extension).equals(new ExtensionWithId("id", extension)), is(true));
  }

  @Test
  public void hasSameHashCodeAsIdenticalElements() throws Exception {
    assertThat(new ExtensionWithId("id", extension).hashCode(), is(new ExtensionWithId("id", extension).hashCode()));
  }
}