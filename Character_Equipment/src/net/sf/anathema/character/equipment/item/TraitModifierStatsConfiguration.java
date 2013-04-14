package net.sf.anathema.character.equipment.item;

import net.sf.anathema.lib.file.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.TraitModifying;

public class TraitModifierStatsConfiguration extends NewStatsConfiguration {
  public TraitModifierStatsConfiguration() {
    super("Equipment.Creation.Stats.AddTraitModifyingTooltip", new RelativePath("icons/TraitModifying16.png"),
            "EquipmentStats.TraitModifying", TraitModifying);
  }
}