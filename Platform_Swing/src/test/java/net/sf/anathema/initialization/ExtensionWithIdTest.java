package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.Registry;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExtensionWithIdTest {

  ResourceLoader loader = mock(ResourceLoader.class);
  AnnotationFinder finder = mock(AnnotationFinder.class);
  IApplicationModel model = mock(IApplicationModel.class);
  IRepository repository = mock(IRepository.class);
  IAnathemaExtension extension = mock(IAnathemaExtension.class);

  @Test
  public void handsAllParametersToExtension() throws Exception {
    when(model.getRepository()).thenReturn(repository);
    when(model.getExtensionPointRegistry()).thenReturn(new Registry<String, IAnathemaExtension>());
    new ExtensionWithId("id", extension).register(model, finder, loader);
    verify(extension).initialize(repository, finder, loader);
  }
}