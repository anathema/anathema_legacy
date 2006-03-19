package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.lib.registry.Registry;

public class ItemTypeViewPropertiesExtensionPoint extends Registry<IItemType, IItemTypeViewProperties> implements IExtensionPoint {
  
  public static final String ID = ItemTypeViewPropertiesExtensionPoint.class.getName();  
}