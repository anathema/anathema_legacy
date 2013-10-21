package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.icon.EmptyIcon;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.framework.environment.Resources;

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
    RelativePath icon = propertiesMap.get((IItemType) value).getIcon();
    if (icon == AgnosticUIConfiguration.NO_ICON) {
      return new EmptyIcon();
    }
    return new ImageProvider().getImageIcon(icon);
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