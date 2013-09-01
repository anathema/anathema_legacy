package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.extension.IAnathemaExtension;

public class ExtensionWithId {

  private final String id;
  private final IAnathemaExtension extension;

  public ExtensionWithId(String id, IAnathemaExtension extension) {
    this.id = id;
    this.extension = extension;
  }

  public void register(IApplicationModel model, Environment environment) throws InitializationException {
    extension.initialize(model.getRepository(), environment, environment);
    model.getExtensionPointRegistry().register(id, extension);
  }
}