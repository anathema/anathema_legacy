package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentStatsUIConfiguration implements TechnologyAgnosticUIConfiguration<IEquipmentStats> {
  private final EquipmentStringBuilder tooltipFactory;

  public EquipmentStatsUIConfiguration(IResources resources) {
    this.tooltipFactory = new EquipmentStringBuilder(resources);
  }

  @Override
  public String getIconsRelativePath(IEquipmentStats value) {
    if (value instanceof IWeaponStats) {
      if (((IWeaponStats) value).isRangedCombat()) {
        return getStandardIcon(EquipmentStatisticsType.RangedCombat);
      }
      return getStandardIcon(EquipmentStatisticsType.CloseCombat);
    }
    if (value instanceof IArmourStats) {
      return getStandardIcon(EquipmentStatisticsType.Armor);
    }
    if (value instanceof IArtifactStats) {
      return getStandardIcon(EquipmentStatisticsType.Artifact);
    }
    if (value instanceof ITraitModifyingStats) {
      return getStandardIcon(EquipmentStatisticsType.TraitModifying);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong.");
  }

  private String getStandardIcon(EquipmentStatisticsType type) {
    return "icons/" + type.name() + "16.png";
  }

  @Override
  public String getLabel(IEquipmentStats value) {
    return value.getName().getId();
  }

  @Override
  public String getToolTipText(IEquipmentStats value) {
    return tooltipFactory.createString(null, value);
  }
}
