package net.sf.anathema.framework.module;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnathemaModule implements IAnathemaModule {

  private IAnathemaView anathemaView;
  private IAnathemaModel anathemaModel;
  private List<AbstractItemTypeConfiguration> itemTypeConfigurations = new ArrayList<AbstractItemTypeConfiguration>();

  protected AbstractAnathemaModule() {
    // Nothing to do
  }

  protected final void addItemTypeConfiguration(AbstractItemTypeConfiguration typeConfiguration) {
    itemTypeConfigurations.add(typeConfiguration);
  }

  public void initModelExtensionPoints(IRegistry<String, IExtensionPoint> registry, IAnathemaModel model, IResources resources) {
    // Nothing to do
  }

  public void fillModelExtensionPoints(IRegistry<String, IExtensionPoint> extensionPointRegistry, IAnathemaModel model) {
    // Nothing to do
  }

  public final void initItemTypes(IItemTypeRegistry itemRegistry) {
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      itemRegistry.registerItemType(configuration.getItemType());
    }
  }

  public void initModel(IAnathemaModel model, IResources resources) {
    this.anathemaModel = model;
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.initModel(model);
    }
  }

  public void initPresentationExtensionPoints(IRegistry<String, IExtensionPoint> registry, IResources resources) {
    // Nothing to do
  }

  public void fillPresentationExtensionPoints(
      IRegistry<String, IExtensionPoint> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.fillPresentationExtensionPoints(extensionPointRegistry, resources, model, view);
    }
  }

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    this.anathemaView = view;
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerViewFactory(getAnathemaModel(), resources);
    }
  }

  protected final IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected final IAnathemaView getAnathemaView() {
    return anathemaView;
  }
}