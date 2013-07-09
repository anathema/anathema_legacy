package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.ItemTypeUi;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class ItemTypeTreeUi implements ObjectUi<Object> {

  private final ObjectUi<Object> itemtypeUi;
  private final ItemTypePropertiesMap propertiesMap;

  public ItemTypeTreeUi(Resources resources, ItemTypePropertiesMap propertiesMap) {
    this.propertiesMap = propertiesMap;
    this.itemtypeUi = new ItemTypeUi(resources, propertiesMap);
  }

  @Override
  public Icon getIcon(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getIcon(value);
    }
    if (value instanceof PrintNameFile) {
      PrintNameFile printNameFile = (PrintNameFile) value;
      RelativePath iconPath = getItemTypeUi(printNameFile).getIconsRelativePath(printNameFile);
      return new ImageProvider().getImageIcon(iconPath);
    }
    return null;
  }

  private AgnosticUIConfiguration<PrintNameFile> getItemTypeUi(PrintNameFile value) {
    return propertiesMap.get(value.getItemType()).getItemTypeUI();
  }

  @Override
  public String getLabel(Object value) {
    if (value instanceof IItemType) {
      return itemtypeUi.getLabel(value);
    }
    if (value instanceof PrintNameFile) {
      PrintNameFile printNameFile = (PrintNameFile) value;
      return getItemTypeUi(printNameFile).getLabel(printNameFile);
    }
    return value.toString();
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}