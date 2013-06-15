package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.ISpecialty;

public class EquipmentSpecialtyOption implements IEquipmentStatsOption {

  private final ISpecialty specialty;
  private final ITraitType type;

  public EquipmentSpecialtyOption(ISpecialty specialty, ITraitType type) {
    this.specialty = specialty;
    this.type = type;
  }

  @Override
  public String getName() {
    return specialty.getName();
  }

  @Override
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

  @Override
  public ISpecialty getUnderlyingTrait() {
    return specialty;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof EquipmentSpecialtyOption) {
      return specialty.equals(((EquipmentSpecialtyOption) obj).specialty);
    }
    return false;
  }

}
