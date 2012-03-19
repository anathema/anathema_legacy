package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentCharacterOptionProvider {
	public void enableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);
	
	public void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);
	
	public boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);
	
	public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentItem item, IEquipmentStats stats);
	
	public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentStats stats);
	
	// returns true if options have been transferred
	public boolean transferOptions(IEquipmentItem fromItem, IEquipmentItem toItem);
}
