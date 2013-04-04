package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class EquipmentUI extends AbstractUI {
  private static final int ICON_SIZE = 20;

  public static String getIconName(EquipmentStatisticsType type) {
    return "icons/" + type.name() + ICON_SIZE + ".png";
  }

  public Icon getIcon(EquipmentStatisticsType type) {
    return getIcon(getIconName(type)); //$NON-NLS-1$
  }

  public Icon getRefreshIcon() {
    return getIcon("icons/ButtonRefresh16.png"); //$NON-NLS-1$
  }
}