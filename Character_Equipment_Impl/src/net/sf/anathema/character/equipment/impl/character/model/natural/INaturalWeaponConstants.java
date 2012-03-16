package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Clinch;
import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Punch;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface INaturalWeaponConstants {
  public static final IEquipmentStats[] SECOND_EDITION = new IEquipmentStats[] {
          new Punch(),
          new Kick(),
          new Clinch() };
}