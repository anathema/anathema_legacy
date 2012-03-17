package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class EquipmentSpecialtyOption implements IEquipmentStatsOption {

	private final INamedGenericTrait specialty;
	private final ITraitType type;
	
	public EquipmentSpecialtyOption(INamedGenericTrait specialty, ITraitType type)
	{
		this.specialty = specialty;
		this.type = type;
	}
	
	public String getName() {
		return specialty.getName();
	}
	
	public String getType() {
		return type.getId();
	}
	
	@Override
	public int getAccuracyModifier() {
		return specialty.getCurrentValue();
	}

	@Override
	public int getDefenseModifier() {
		return specialty.getCurrentValue();
	}
	
	public INamedGenericTrait getUnderlyingTrait() {
		return specialty;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EquipmentSpecialtyOption)
			return specialty.equals(((EquipmentSpecialtyOption)obj).specialty);
		return false;
	}

}
