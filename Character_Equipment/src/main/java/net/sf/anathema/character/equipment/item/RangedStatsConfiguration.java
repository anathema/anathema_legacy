package net.sf.anathema.character.equipment.item;

import net.sf.anathema.lib.file.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.RangedCombat;

public class RangedStatsConfiguration extends NewStatsConfiguration {
  public RangedStatsConfiguration() {
    super("Equipment.Creation.Stats.AddRangedTooltip", new RelativePath("icons/RangedCombat16.png"),
            "EquipmentStats.RangedCombat", RangedCombat);
  }
}