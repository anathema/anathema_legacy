package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;

public interface IEquipmentStatsCreationFactory {

  IEquipmentStats editStats(Component parentComponent, Resources resources, String[] nameArray,
                            IEquipmentStats selectedStats);

  IEquipmentStats createNewStatsQuickly(String[] definedNames, String nameProposal, EquipmentStatisticsType type);

  boolean canHaveThisKindOfStats(EquipmentStatisticsType type, MaterialComposition materialComposition);
}
