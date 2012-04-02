package net.sf.anathema.character.equipment.item;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public final class EquipmentStatsUi implements IObjectUi<Object> {

  private final EquipmentUI equipmentUI;

  public EquipmentStatsUi(IResources resources) {
    this.equipmentUI = new EquipmentUI(resources);
  }

  @Override
  public String getLabel(Object value) {
	IEquipmentStats stats = (IEquipmentStats)value;
    return stats.getName().getId();
  }

  @Override
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
    if (value instanceof IArtifactStats) {
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.Artifact);
    }
    if (value instanceof ITraitModifyingStats) {
      return equipmentUI.getStandardIcon(EquipmentStatisticsType.TraitModifying);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong."); //$NON-NLS-1$
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}