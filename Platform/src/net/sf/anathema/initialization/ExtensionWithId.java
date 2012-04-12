package net.sf.anathema.initialization;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.lib.resources.IResourceDataManager;

public class ExtensionWithId {

  private final String id;
  private final IAnathemaExtension extension;

  public ExtensionWithId(String id, IAnathemaExtension extension) {
    this.id = id;
    this.extension = extension;
  }

  public void register(AnathemaModel model, IResourceDataManager resourceDataManager, AnathemaReflections reflections, Instantiater instantiater) throws InitializationException {
    extension.initialize(resourceDataManager, model.getRepository(), reflections, instantiater);
    model.getExtensionPointRegistry().register(id, extension);
  }
}