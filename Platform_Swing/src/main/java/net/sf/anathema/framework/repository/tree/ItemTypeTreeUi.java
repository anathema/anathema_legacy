package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.ItemTypeUi;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.icon.ImageProvider;
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
      RelativePath iconPath = getItemTypeUi((PrintNameFile) value).getIconsRelativePath(value);
      return new ImageProvider().getImageIcon(iconPath);
    }
    return null;
  }

  private AgnosticUIConfiguration getItemTypeUi(PrintNameFile value) {
    return extension.get(value.getItemType()).getItemTypeUI();
  }

  @Override
  public String getLabel(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getLabel(value);
    }
    if (value instanceof PrintNameFile) {
      return getItemTypeUi((PrintNameFile) value).getLabel(value);
    }
    return value.toString();
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}