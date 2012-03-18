package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IEquipmentCharacterDataProvider extends IEquipmentCharacterOptionProvider
{
	public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item);
	
	public IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);
	
	public INamedGenericTrait[] getSpecialties(ITraitType trait);
}
