package net.sf.anathema.framework.presenter.action;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.lib.resources.IResources;

public class ItemTypeUi implements IObjectUi {
  private final ItemTypeCreationViewPropertiesExtensionPoint extension;
  private final IResources resources;

  public ItemTypeUi(IResources resources, ItemTypeCreationViewPropertiesExtensionPoint extension) {
    this.resources = resources;
    this.extension = extension;
  }

  public Icon getIcon(Object value) {
    return extension.get((IItemType) value).getIcon();
  }

  public String getLabel(Object value) {
    return resources.getString(extension.get((IItemType) value).getLabelKey());
  }
}