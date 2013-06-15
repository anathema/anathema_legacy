package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.ISpecialty;
import net.sf.anathema.lib.control.IChangeListener;

public interface IEquipmentCharacterDataProvider extends ItemAttunementEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  ISpecialty[] getSpecialties(ITraitType trait);

  void addCharacterSpecialtyListChangeListener(IChangeListener listener);
}
