package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnathemaModule implements IAnathemaModule {

  private IAnathemaModel anathemaModel;

  public void initModel(IAnathemaModel model) {
    this.anathemaModel = model;
  }

  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    // Nothing to do
  }

  protected final IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }
}