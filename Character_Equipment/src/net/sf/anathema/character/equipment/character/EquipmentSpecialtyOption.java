package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;

public class EquipmentSpecialtyOption implements IEquipmentStatsOption {

  private final Specialty specialty;
  private final ITraitType type;

  public EquipmentSpecialtyOption(Specialty specialty, ITraitType type) {
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
  public Specialty getUnderlyingTrait() {
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
