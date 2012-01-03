package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal.WeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourTableEncoder;

public class LunarEquipmentEncoders {

  public static WeaponryTableEncoder CreateWeaponryEncoder() {
    return new WeaponryTableEncoder(LunarWeaponryContent.class);
  }

  public static ArmourTableEncoder CreateArmourEncoder() {
    return new ArmourTableEncoder(LunarArmourContent.class);
  }
}
