package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private final ICloseCombatStatsticsModel closeCombatStatisticsModel = new CloseCombatStatsticsModel();
  private final IRangedCombatStatisticsModel rangedWeaponStatisticsModel = new RangedWeaponStatisticsModel();
  private final IShieldStatisticsModel shieldStatisticsModel = new ShieldStatisticsModel();
  private final IArmourStatisticsModel armourStatisticsModel = new ArmourStatsticsModel();
  private final ChangeControl equpimentTypeChangeControl = new ChangeControl();
  private final IWeaponTagsModel weaponTagsModel = new WeaponTagsModel();
  private EquipmentStatisticsType statisticsType;

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

  public IWeaponTagsModel getWeaponTagsModel() {
    return weaponTagsModel;
  }

  public IRangedCombatStatisticsModel getRangedWeaponStatisticsModel() {
    return rangedWeaponStatisticsModel;
  }

  public IShieldStatisticsModel getShieldStatisticsModel() {
    return shieldStatisticsModel;
  }

  public IArmourStatisticsModel getArmourStatisticsModel() {
    return armourStatisticsModel;
  }

  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }
}