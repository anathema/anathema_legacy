package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.control.ChangeListener;

public interface EquipmentHeroEvaluator extends ItemAttunementEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  Specialty[] getSpecialties(TraitType trait);

  void addCharacterSpecialtyListChangeListener(ChangeListener listener);
}
