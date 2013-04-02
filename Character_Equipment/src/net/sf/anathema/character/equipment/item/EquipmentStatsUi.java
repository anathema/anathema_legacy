package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public final class EquipmentStatsUi implements ObjectUi<IEquipmentStats> {

  private final EquipmentUI equipmentUI;
  private final EquipmentStringBuilder tooltipFactory;

  public EquipmentStatsUi(IResources resources) {
    this.equipmentUI = new EquipmentUI(resources);
    this.tooltipFactory = new EquipmentStringBuilder(resources);
  }

  @Override
  public String getLabel(IEquipmentStats value) {
    return value.getName().getId();
  }

  @Override
  public Icon getIcon(IEquipmentStats value) {
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
  public String getToolTipText(IEquipmentStats value) {
    return tooltipFactory.createString(null, value);
  }
}