package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class EquipmentUI extends AbstractUI {
  private static final int ICON_SIZE = 20;

  public static RelativePath getIconName(EquipmentStatisticsType type) {
    return new RelativePath("icons/" + type.name() + ICON_SIZE + ".png");
  }

  public Icon getRefreshIcon() {
    return getIcon(new RelativePath("icons/ButtonRefresh16.png"));
  }

  public Icon getIcon(EquipmentStatisticsType type) {
    return getIcon(getIconName(type));
  }
}