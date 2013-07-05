package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.generic.equipment.ArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentStatsUIConfiguration extends AbstractUIConfiguration<IEquipmentStats> {
  private final EquipmentStringBuilder tooltipFactory;

  public EquipmentStatsUIConfiguration(Resources resources) {
    this.tooltipFactory = new EquipmentStringBuilder(resources);
  }

  @Override
  protected RelativePath iconForExistingValue(IEquipmentStats value) {
    if (value instanceof IWeaponStats) {
      if (((IWeaponStats) value).isRangedCombat()) {
        return new RelativePath("icons/RangedCombat16.png");
      }
      return new RelativePath("icons/CloseCombat16.png");
    }
    if (value instanceof IArmourStats) {
      return new RelativePath("icons/Armor16.png");
    }
    if (value instanceof ArtifactStats) {
      return new RelativePath("icons/Artifact16.png");
    }
    if (value instanceof ITraitModifyingStats) {
      return new RelativePath("icons/TraitModifying16.png");
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong.");
  }

  @Override
  protected String labelForExistingValue(IEquipmentStats value) {
    return value.getName().getId();
  }

  @Override
  protected String tooltipForExistingValue(IEquipmentStats value) {
    return tooltipFactory.createString(null, value);
  }
}