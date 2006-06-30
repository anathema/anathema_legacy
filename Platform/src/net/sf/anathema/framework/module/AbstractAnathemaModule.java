package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnathemaModule implements IAnathemaModule {

  private IAnathemaView anathemaView;
  private IAnathemaModel anathemaModel;

  public void initModelExtensionPoints(
      IRegistry<String, IAnathemaExtension> registry,
      IAnathemaModel model,
      IResources resources) {
    // Nothing to do
  }

  public void fillModelExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model) {
    // Nothing to do
  }

  public void initModel(IAnathemaModel model, IResources resources) {
    this.anathemaModel = model;
  }

  public void initPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> registry, IResources resources) {
    // Nothing to do
  }

  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    // Nothing to do
  }

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    this.anathemaView = view;
  }

  protected final IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected final IAnathemaView getAnathemaView() {
    return anathemaView;
  }
}