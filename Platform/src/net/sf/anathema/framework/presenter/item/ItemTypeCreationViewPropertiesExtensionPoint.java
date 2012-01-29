package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

@Extension(id="net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint")
public class ItemTypeCreationViewPropertiesExtensionPoint extends Registry<IItemType, IItemTypeViewProperties> implements
    IAnathemaExtension {

  public static final String ID = ItemTypeCreationViewPropertiesExtensionPoint.class.getName();

  public void initialize(IResources resources, IDataFileProvider dataFileProvider, Instantiater instantiater) {
    // nothing to do
  }
}