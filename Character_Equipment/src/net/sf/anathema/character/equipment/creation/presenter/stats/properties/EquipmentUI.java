package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

public class EquipmentUI extends AbstractUI {
  private static final int ICON_SIZE = 20;

  public RelativePath getIcon(EquipmentStatisticsType type) {
    return new RelativePath("icons/" + type.name() + ICON_SIZE + ".png");
  }

  public RelativePath getRefreshIconPath() {
    return new RelativePath("icons/ButtonRefresh16.png");
  }
}