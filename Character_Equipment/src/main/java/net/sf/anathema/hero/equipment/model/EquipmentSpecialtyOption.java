package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;

public class EquipmentSpecialtyOption implements IEquipmentStatsOption {

  private final Specialty specialty;
  private final TraitType type;

  public EquipmentSpecialtyOption(Specialty specialty, TraitType type) {
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
    return obj instanceof EquipmentSpecialtyOption && specialty.equals(((EquipmentSpecialtyOption) obj).specialty);
  }

}
