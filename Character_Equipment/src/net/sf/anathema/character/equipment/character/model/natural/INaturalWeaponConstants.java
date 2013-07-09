package net.sf.anathema.character.equipment.character.model.natural;

import net.sf.anathema.character.equipment.character.model.natural.secondedition.Clinch;
import net.sf.anathema.character.equipment.character.model.natural.secondedition.Kick;
import net.sf.anathema.character.equipment.character.model.natural.secondedition.Punch;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;

public interface INaturalWeaponConstants {
  IEquipmentStats[] SECOND_EDITION = new IEquipmentStats[]{new Punch(), new Kick(), new Clinch()};
}