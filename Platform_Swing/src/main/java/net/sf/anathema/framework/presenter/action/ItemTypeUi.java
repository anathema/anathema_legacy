package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class ItemTypeUi implements ObjectUi<Object> {
  private final ItemTypeCreationViewPropertiesExtensionPoint extension;
  private final Resources resources;

  public ItemTypeUi(Resources resources, ItemTypeCreationViewPropertiesExtensionPoint extension) {
    this.resources = resources;
    this.extension = extension;
  }

  @Override
  public Icon getIcon(Object value) {
    return new ImageProvider().getImageIcon(extension.get((IItemType) value).getIcon());
  }

  @Override
  public String getLabel(Object value) {
    return resources.getString(extension.get((IItemType) value).getLabelKey());
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}