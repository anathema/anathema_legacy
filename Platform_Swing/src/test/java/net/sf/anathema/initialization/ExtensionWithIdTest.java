package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.Registry;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExtensionWithIdTest {

  ResourceLoader loader = mock(ResourceLoader.class);
  ObjectFactory finder = mock(ObjectFactory.class);
  IApplicationModel model = mock(IApplicationModel.class);
  Repository repository = mock(Repository.class);
  IAnathemaExtension extension = mock(IAnathemaExtension.class);

  @Test
  public void handsAllParametersToExtension() throws Exception {
    when(model.getRepository()).thenReturn(repository);
    when(model.getExtensionPointRegistry()).thenReturn(new Registry<String, IAnathemaExtension>());
    new ExtensionWithId("id", extension).register(model, loader);
    verify(extension).initialize(repository, finder, loader);
  }
}