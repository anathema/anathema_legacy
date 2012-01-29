package net.sf.anathema.initialization;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public class ExtensionWithId {

  private final String id;
  private final IAnathemaExtension extension;

  public ExtensionWithId(String id, IAnathemaExtension extension) {
    this.id = id;
    this.extension = extension;
  }

  public void register(AnathemaModel model, IResources resources, Instantiater instantiater) throws InitializationException {
    extension.initialize(resources, model.getRepository(), instantiater);
    model.getExtensionPointRegistry().register(id, extension);
  }
}