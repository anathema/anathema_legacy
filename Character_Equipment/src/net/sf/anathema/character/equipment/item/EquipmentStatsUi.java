package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public final class EquipmentStatsUi implements IObjectUi {

  private final EquipmentUI equipmentUI;

  public EquipmentStatsUi(IResources resources) {
    equipmentUI = new EquipmentUI(resources);
  }

  public String getLabel(Object value) {
    return ((IEquipmentStats) value).getName().getId();
  }

  public Icon getIcon(Object value) {
    if (value instanceof IWeaponStats) {
      if (((IWeaponStats) value).isRangedCombat()) {
        return equipmentUI.getStandardIcon(EquipmentStatisticsType.RangedCombat);
      }
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.CloseCombat);
    }
    if (value instanceof IArmourStats) {
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.Armor);
    }
    if (value instanceof IShieldStats) {
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.Shield);
    }
    if (value instanceof IArtifactStats)
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.Artifact);
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong."); //$NON-NLS-1$
  }
}