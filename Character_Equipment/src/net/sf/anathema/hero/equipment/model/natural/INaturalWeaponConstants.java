package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;

public interface INaturalWeaponConstants {
  IEquipmentStats[] SECOND_EDITION = new IEquipmentStats[]{new Punch(), new Kick(), new Clinch()};
}