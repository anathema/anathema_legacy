package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeCreationViewProperties;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

public class ItemTypeCreationViewPropertiesExtensionPoint extends Registry<IItemType, IItemTypeCreationViewProperties> implements
    IAnathemaExtension {

  public static final String ID = ItemTypeCreationViewPropertiesExtensionPoint.class.getName();

  public void initialize(IResources resources, IDataFileProvider dataFileProvider) {
    // nothing to do
  }
}