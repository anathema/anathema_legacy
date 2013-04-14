package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface EquipmentStatsFactory {

  IEquipmentStats createNewStats(String[] definedNames, String nameProposal, EquipmentStatisticsType type);

  boolean canHaveThisKindOfStats(EquipmentStatisticsType type, MaterialComposition materialComposition);
}
