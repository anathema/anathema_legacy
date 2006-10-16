package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentUI extends AbstractUI {
  private static final int ICON_SIZE = 20;
  private static final int STANDARD_ICON_SIZE = 16;

  public EquipmentUI(IResources resources) {
    super(resources);
  }

  public Icon getIcon(EquipmentStatisticsType type) {
    return getIcon(type.name() + ICON_SIZE + ".png"); //$NON-NLS-1$    
  }

  public Icon getStandardIcon(EquipmentStatisticsType type) {
    return getIcon(type.name() + STANDARD_ICON_SIZE + ".png"); //$NON-NLS-1$    
  }

  public Icon getRefreshIcon() {
    return getIcon("ButtonRefresh16.png"); //$NON-NLS-1$
  }
}