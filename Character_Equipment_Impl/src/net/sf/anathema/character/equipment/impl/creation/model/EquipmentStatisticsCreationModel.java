package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private EquipmentStatisticsType statisticsType;
  private final ICloseCombatStatsticsModel closeCombatStatisticsModel = new CloseCombatStatsticsModel();
  private final ChangeControl equpimentTypeChangeControl = new ChangeControl();

  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    if (this.statisticsType == statisticsType) {
      return;
    }
    this.statisticsType = statisticsType;
    equpimentTypeChangeControl.fireChangedEvent();
  }

  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel() {
    return closeCombatStatisticsModel;
  }

  public void addEquipmentTypeChangeListener(IChangeListener changeListener) {
    equpimentTypeChangeControl.addChangeListener(changeListener);
  }

  public boolean isEquipmentTypeSelected(EquipmentStatisticsType type) {
    return this.statisticsType == type;
  }
}