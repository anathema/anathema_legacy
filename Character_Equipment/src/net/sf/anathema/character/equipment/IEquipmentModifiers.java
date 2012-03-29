package net.sf.anathema.character.equipment;

public interface IEquipmentModifiers {
  int getPDVMod();

  int getMeleeSpeedMod();

  int getMeleeAccuracyMod();

  int getMeleeDamageMod();

  int getMeleeRateMod();

  int getRangedSpeedMod();

  int getRangedAccuracyMod();

  int getRangedDamageMod();

  int getRangedRateMod();
}
