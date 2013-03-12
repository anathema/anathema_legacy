package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;

public class ExtensionWithId {

  private final String id;
  private final IAnathemaExtension extension;

  public ExtensionWithId(String id, IAnathemaExtension extension) {
    this.id = id;
    this.extension = extension;
  }

  public void register(IApplicationModel model, AnnotationFinder finder, ResourceLoader loader) throws InitializationException {
    extension.initialize(model.getRepository(), finder, loader);
    model.getExtensionPointRegistry().register(id, extension);
  }
}