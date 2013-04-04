package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.ItemTypeUi;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class ItemTypeTreeUi implements ObjectUi<Object> {

  private final ItemTypeCreationViewPropertiesExtensionPoint extension;
  private final ObjectUi<Object> itemtypeUi;

  public ItemTypeTreeUi(Resources resources, ItemTypeCreationViewPropertiesExtensionPoint extension) {
    this.extension = extension;
    this.itemtypeUi = new ItemTypeUi(resources, extension);
  }

  @Override
  public Icon getIcon(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getIcon(value);
    }
    if (value instanceof PrintNameFile) {
      return extension.get(((PrintNameFile) value).getItemType()).getItemTypeUI().getIcon(value);
    }
    return null;
  }

  @Override
  public String getLabel(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getLabel(value);
    }
    if (value instanceof PrintNameFile) {
      return extension.get(((PrintNameFile) value).getItemType()).getItemTypeUI().getLabel(value);
    }
    return value.toString();
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}