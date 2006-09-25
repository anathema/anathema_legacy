package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.natural.corerules.Hold;
import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Clinch;
import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.secondedition.Punch;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface INaturalWeaponConstants {
  public static final IEquipmentStats[] CORE_RULES = new IEquipmentStats[] {
      new net.sf.anathema.character.equipment.impl.character.model.natural.corerules.Punch(),
      new net.sf.anathema.character.equipment.impl.character.model.natural.corerules.Kick(),
      new net.sf.anathema.character.equipment.impl.character.model.natural.corerules.Clinch(),
      new Hold() };

  public static final IEquipmentStats[] POWER_COMBAT = new IEquipmentStats[] {
      new net.sf.anathema.character.equipment.impl.character.model.natural.powercombat.Punch(),
      new net.sf.anathema.character.equipment.impl.character.model.natural.powercombat.Kick(),
      new net.sf.anathema.character.equipment.impl.character.model.natural.powercombat.Clinch() };
  public static final IEquipmentStats[] SECOND_EDITION = new IEquipmentStats[] { new Punch(), new Kick(), new Clinch() };
}