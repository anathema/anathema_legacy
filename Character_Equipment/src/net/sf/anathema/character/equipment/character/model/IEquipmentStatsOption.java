package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.library.trait.specialties.ISpecialty;

public interface IEquipmentStatsOption {

  String getName();

  String getType();

  int getAccuracyModifier();

  int getDefenseModifier();

  ISpecialty getUnderlyingTrait();
}
