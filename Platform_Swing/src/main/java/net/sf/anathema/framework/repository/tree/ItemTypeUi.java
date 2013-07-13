package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class ItemTypeUi implements ObjectUi<Object> {
  private final Resources resources;
  private ItemTypePropertiesMap propertiesMap;

  public ItemTypeUi(Resources resources, ItemTypePropertiesMap propertiesMap) {
    this.resources = resources;
    this.propertiesMap = propertiesMap;
  }

  @Override
  public Icon getIcon(Object value) {
    return new ImageProvider().getImageIcon(propertiesMap.get((IItemType) value).getIcon());
  }

  @Override
  public String getLabel(Object value) {
    return resources.getString(propertiesMap.get((IItemType) value).getLabelKey());
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}