package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

public class ItemTypeViewPropertiesExtensionPoint extends Registry<IItemType, IItemTypeViewProperties> implements IAnathemaExtension {

  public static final String ID = ItemTypeViewPropertiesExtensionPoint.class.getName();  
  
  public void initialize(IResources resources) {
    // nothing to do
  }
}