package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentStatsUIConfiguration implements TechnologyAgnosticUIConfiguration<IEquipmentStats> {
  private final EquipmentStringBuilder tooltipFactory;

  public EquipmentStatsUIConfiguration(Resources resources) {
    this.tooltipFactory = new EquipmentStringBuilder(resources);
  }

  @Override
  public String getIconsRelativePath(IEquipmentStats value) {
    if (value instanceof IWeaponStats) {
      if (((IWeaponStats) value).isRangedCombat()) {
        return "icons/RangedCombat16.png";
      }
      return "icons/CloseCombat16.png";
    }
    if (value instanceof IArmourStats) {
      return "icons/Armor16.png";
    }
    if (value instanceof IArtifactStats) {
      return "icons/Artifact16.png";
    }
    if (value instanceof ITraitModifyingStats) {
      return "icons/TraitModifying16.png";
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong.");
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
