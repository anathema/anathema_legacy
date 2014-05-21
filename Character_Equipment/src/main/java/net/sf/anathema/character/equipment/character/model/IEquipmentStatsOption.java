package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.hero.specialties.Specialty;

public interface IEquipmentStatsOption {

  String getName();

  String getType();

  int getAccuracyModifier();

  int getDefenseModifier();

  Specialty getUnderlyingTrait();
}
