package net.sf.anathema.character.equipment.item;

import net.sf.anathema.lib.file.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.CloseCombat;

public class MeleeStatsConfiguration extends NewStatsConfiguration {
  public MeleeStatsConfiguration() {
    super("Equipment.Creation.Stats.AddMeleeTooltip", new RelativePath("icons/CloseCombat16.png"),
            "EquipmentStats.CloseCombat", CloseCombat);
  }
}