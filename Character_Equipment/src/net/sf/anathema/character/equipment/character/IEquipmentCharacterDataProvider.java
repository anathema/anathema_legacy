package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ISpecialtyListChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IEquipmentCharacterDataProvider extends ItemAttunementEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  INamedGenericTrait[] getSpecialties(ITraitType trait);

  void addCharacterSpecialtyListChangeListener(ISpecialtyListChangeListener listener);
}
