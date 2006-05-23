package net.sf.anathema.framework.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.resources.StringProvider;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public abstract class AbstractAnathemaModule implements IAnathemaModule {

  private IAnathemaView anathemaView;
  private IAnathemaModel anathemaModel;
  private List<AbstractItemTypeConfiguration> itemTypeConfigurations = new ArrayList<AbstractItemTypeConfiguration>();
  private IResources anathemaResources;

  protected AbstractAnathemaModule() {
    // Nothing to do
  }

  protected final void addItemTypeConfiguration(AbstractItemTypeConfiguration typeConfiguration) {
    itemTypeConfigurations.add(typeConfiguration);
  }

  public void initAnathemaResources(IAnathemaResources resources) {
    this.anathemaResources = resources;
  }

  public void initModelExtensionPoints(IRegistry<String, IExtensionPoint> registry, IAnathemaModel model) {
    // Nothing to do
  }

  public void fillModelExtensionPoints(IRegistry<String, IExtensionPoint> extensionPointRegistry, IAnathemaModel model) {
    // Nothing to do
  }

  public void initItemTypes(IItemTypeRegistry itemRegistry) {
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      itemRegistry.registerItemType(configuration.getItemType());
    }
  }

  public void initModel(IAnathemaModel model) {
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

  protected final IStringResourceHandler createStringProvider(String propertiesName, Locale locale) {
    return new StringProvider("language." + propertiesName, locale); //$NON-NLS-1$
  }

  protected final IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected final IAnathemaView getAnathemaView() {
    return anathemaView;
  }

  protected final IResources getResources() {
    return anathemaResources;
  }
}