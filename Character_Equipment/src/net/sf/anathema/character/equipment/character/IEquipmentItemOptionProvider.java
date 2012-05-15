package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;

public interface IEquipmentItemOptionProvider {
	IEquipmentStatsOption[] getEnabledStatOptions();
}
